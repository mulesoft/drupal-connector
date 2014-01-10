/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

package org.mule.modules.drupal.client;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;

import org.apache.commons.lang.StringUtils;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.modules.drupal.DrupalCollection;
import org.mule.modules.drupal.GsonFactory;
import org.mule.modules.drupal.model.Comment;
import org.mule.modules.drupal.model.CommentRequest;
import org.mule.modules.drupal.model.CountRequest;
import org.mule.modules.drupal.model.DrupalEntity;
import org.mule.modules.drupal.model.File;
import org.mule.modules.drupal.model.Node;
import org.mule.modules.drupal.model.NodeRequest;
import org.mule.modules.drupal.model.TaxonomyTerm;
import org.mule.modules.drupal.model.TaxonomyVocabulary;
import org.mule.modules.drupal.model.TaxonomyVocabularyRequest;
import org.mule.modules.drupal.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;
import com.sun.jersey.multipart.impl.MultiPartWriter;
/**
 * Drupal client to interact with the rest server provided with the service modulo.
 * 
 * @author pablocabrera
 *
 */
public class DrupalRestClient implements DrupalClient {

	private Client client;
	private String server;
	private String apiUrl;
	private int port;
	private NewCookie sessionId;

	private static final String RELATIONSHIP_FILES = "files";
	private static final String RELATIONSHIP_COMMENTS = "comments";
	private static final String COUNT_ALL = "countAll";
	private static final String COUNT_NEW = "countNew";
	private static final String REGISTER = "register";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	
	private static final String ACTION_GETTREE = "getTree";
	private static final String ACTION_SELECTNODES = "selectNodes";
	private static final String ACTION_ATTACHFILE = "attach_file";
	
	private String username;
	private String password;
	
	public DrupalRestClient(String server, int port, String apiUrl) {
    	ClientConfig config = new DefaultClientConfig();
    	config.getClasses().add(MultiPartWriter.class);
        
    	client = Client.create(config);
		this.server = server;
		this.apiUrl = apiUrl;
		this.port = port;
	}

	public Client getClient() {
		if (client == null) {
			client = Client.create();
		}
		return client;
	}

	private WebResource getWebResource() throws DrupalException {
		WebResource r;

		try {
			r = this.client.resource(new URI("http", null, server, port, apiUrl,
					null, null));
		} catch (URISyntaxException e) {
			throw new DrupalException(e);
		}

		return r;
	}

	public Node readNode(String nodeId) throws DrupalException {
		Node node = null;
		node = (Node) readOne(DrupalCollection.Node, nodeId);
		return node;
	}

	@Override
	public void login(String username, String password)
			throws ConnectionException {
		Map<String, String> loginCredentials = new HashMap<String, String>();
		loginCredentials.put("username", username);
		loginCredentials.put("password", password);

		this.username=username;
		this.password=password;
		
		String request = GsonFactory.getGson().toJson(loginCredentials);

		WebResource r;
		try {
			r = this.client.resource(new URI("http", null, server, port, apiUrl
					+"/"+DrupalCollection.User.getEndpoint() +"/" + LOGIN, null, null));
		} catch (URISyntaxException e) {
			throw new ConnectionException(ConnectionExceptionCode.UNKNOWN,
					null, null);
		}

		ClientResponse response = r.accept(MediaType.APPLICATION_JSON_TYPE)
				.entity(request, MediaType.APPLICATION_JSON_TYPE)
				.post(ClientResponse.class);

		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			String entity = response.getEntity(String.class);
			if (!response.getCookies().isEmpty()) {
				this.sessionId = response.getCookies().get(0);
			} else {
				throw new ConnectionException(ConnectionExceptionCode.UNKNOWN,
						entity, "Drupal rejected login");
			}
		} else if (status == Status.UNAUTHORIZED) {
			throw new ConnectionException(
					ConnectionExceptionCode.INCORRECT_CREDENTIALS, null, null);
		} else {
			throw new ConnectionException(ConnectionExceptionCode.CANNOT_REACH,
					null, null);
		}
	}

	/**
	 * Generic method to create entities in dynamic collections
	 * 
	 * @param collection
	 *            a member of the
	 *            {@link org.mule.modules.drupal.DrupalCollection} enum
	 *            representing the collection that owns the entity
	 * @param entity
	 *            a subclass instance of
	 *            {@link org.mule.modules.drupal.model.DrupalEntity}
	 * @return a subclass instance of
	 *         {@link org.mule.modules.drupal.model.DrupalEntity} with the
	 *         created entity
	 * @throws DrupalException

	 */
	public DrupalEntity create(DrupalCollection collection,
			@Optional @Default("#[payload]") DrupalEntity entity)
			throws DrupalException {
		
		DrupalEntity response = this.executeRequest("POST",
				this.getWebResource().path(collection.getEndpoint())
						, collection,entity);
		
		return response;
	}

	/**
	 * Generic method to read entities from dynamic collections
	 * 
	 * @param collection
	 *            a member of the
	 *            {@link org.mule.modules.drupal.DrupalCollection} enum
	 *            representing the collection that owns the entity
	 * @param objectId
	 *            the id of the entity you want to get
	 * @return a subclass instance of
	 *         {@link org.mule.modules.drupal.model.DrupalEntity} with the
	 *         created entity

	 * @throws DrupalException
	 */
	public DrupalEntity readOne(DrupalCollection collection, String objectId)
			throws DrupalException {
		
		DrupalEntity response = this.executeRequest("GET",
				this.getWebResource().path(collection.getEndpoint())
						.path(objectId), collection,null);

		return response;
	}

	/**
	 * Generic method to update entities from dynamic collections
	 * 
	 * @param collection
	 *            a member of the
	 *            {@link org.mule.modules.drupal.DrupalCollection} enum
	 *            representing the collection that owns the entity
	 * @param objectId
	 *            the id of the entity you want to get
	 * @param entity
	 *            a subclass instance of
	 *            {@link org.mule.modules.drupal.model.DrupalEntity} with
	 *            the entitie's new state
	 * @return a subclass instance of
	 *         {@link org.mule.modules.drupal.model.DrupalEntity} with the
	 *         created entity
	 * @throws DrupalException
	 */
	public DrupalEntity update(DrupalCollection collection, String objectId,
			@Optional @Default("#[payload]") DrupalEntity entity)
			throws DrupalException {

		DrupalEntity response = this.executeRequest("PUT",
				this.getWebResource().path(collection.getEndpoint()).path(objectId)
						, collection,entity);
		return response;
		}

	private <T> List<T> executeListRequest(String method, WebResource r, Type listType, Map<String, String> params) throws DrupalException {
		WebResource.Builder builder = r.accept(MediaType.APPLICATION_JSON_TYPE)
				.cookie(sessionId);

		if(params != null){
			String request = GsonFactory.getGson().toJson(params);
			builder = builder.entity(request,MediaType.APPLICATION_JSON);
		}
		
		ClientResponse response = builder.method(method, ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			
			String json = response.getEntity(String.class);			
			List<T> apiResponse = GsonFactory.getGson().fromJson(json,listType);
			
			return apiResponse;
			
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected."+status.getReasonPhrase(),
					status.getStatusCode()));
		}
	}

	private boolean executeDeleteRequest(WebResource r, DrupalCollection dc, Object entity) throws DrupalException {
		WebResource.Builder builder = r.accept(MediaType.APPLICATION_JSON_TYPE).cookie(sessionId);

		if (entity instanceof DrupalEntity) {
			builder = builder.entity(GsonFactory.getGson().toJson(entity,DrupalEntity.class),
					MediaType.APPLICATION_JSON_TYPE);
		} else if (entity != null) {
			builder = builder.entity(entity, MediaType.APPLICATION_JSON_TYPE);
		}
		
		ClientResponse response = builder.method("DELETE", ClientResponse.class);
		Status status = response.getClientResponseStatus();
		
		if (status == Status.OK) { 
			String json = response.getEntity(String.class);
			Gson gson = GsonFactory.getGson();
			String[] parsed = gson.fromJson(json, String[].class);
			return Boolean.valueOf(parsed[0]);
		}
		else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			String drupalError = response.getEntity(String.class);
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected. Reason:%s. Drupal Error: %s",
					status.getStatusCode(),status.getReasonPhrase(),StringUtils.isEmpty(drupalError) ? "Unknown" : drupalError));
		}
	}
	
	private <T extends DrupalEntity> T executeRequest(String method, WebResource r,
			DrupalCollection dc,Object entity) throws DrupalException {
		WebResource.Builder builder = r.accept(MediaType.APPLICATION_JSON_TYPE)
				.cookie(sessionId);

		if (entity instanceof DrupalEntity) {
			builder = builder.entity(GsonFactory.getGson().toJson(entity,DrupalEntity.class),
					MediaType.APPLICATION_JSON_TYPE);
		} else if (entity != null) {
			builder = builder.entity(entity, MediaType.APPLICATION_JSON_TYPE);
		}
		
		ClientResponse response = builder.method(method, ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			String json = response.getEntity(String.class);
			T apiResponse = null;
			apiResponse = handleResultStatusOK(dc, entity, json, apiResponse);
			return apiResponse;
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			String drupalError = response.getEntity(String.class);
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected. Reason:%s. Drupal Error: %s",
					status.getStatusCode(),status.getReasonPhrase(),StringUtils.isEmpty(drupalError) ? "Unknown" : drupalError));
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends DrupalEntity> T handleResultStatusOK(
			DrupalCollection dc, Object entity, String json, T apiResponse)
			throws DrupalException {
		//The creation of this 2 entities doesnt return an entity id...this is ugly but required
		if(dc!=null && !(entity instanceof TaxonomyTerm) && !(entity instanceof TaxonomyVocabulary)){
			try{
			
			apiResponse=(T) GsonFactory.getGson().fromJson(json,
				dc.getType());
			
			//Check custom fields
			DrupalEntity tempEntity = GsonFactory.getGson().fromJson(json,
					DrupalEntity.class);
			
			apiResponse.setCustomFields(tempEntity.getCustomFields());
			
			}catch(JsonSyntaxException js){
				throw new DrupalException("Error processing Json :" + js.getMessage());
			}
		}
		return apiResponse;
	}

	@Override
	public Comment readComment(String commentId) throws DrupalException {
		Comment comment = null;
		
		comment = (Comment) readOne(DrupalCollection.Comment, commentId);

		return comment;
	}

	@Override
	public User readUser(String userId) throws DrupalException {
		User user = null;
		
		user = (User) readOne(DrupalCollection.User, userId);

		return user;
	}

	@Override
	public TaxonomyTerm readTaxonomyTerm(String taxonomyTermId) throws DrupalException {
		TaxonomyTerm taxonomyTerm = null;
		taxonomyTerm = (TaxonomyTerm) readOne(
					DrupalCollection.TaxonomyTerm, taxonomyTermId);
		return taxonomyTerm;
	}

	@Override
	public File readFile(String fileId) throws DrupalException {
		File file = null;
		
		file = (File) readOne(DrupalCollection.File, fileId);
	
		return file;
	}

	@Override
	public TaxonomyVocabulary readTaxonomyVocabulary(String taxonomyVocabularyId) throws DrupalException {
		TaxonomyVocabulary taxonomyVoc = null;
		
		taxonomyVoc = (TaxonomyVocabulary) readOne(
					DrupalCollection.TaxonomyVocabulary, taxonomyVocabularyId);

		return taxonomyVoc;
	}

	@Override
	public Node createNode(Node node) throws DrupalException {
		NodeRequest n=new NodeRequest();
		n.setNode(node);
		Node createdNode=(Node) create(DrupalCollection.Node, n);
		node.setNid(createdNode.getNid());
		return node;
	}

	@Override
	public Comment createComment(Comment comment) throws DrupalException {
		CommentRequest c=new CommentRequest();
		c.setComment(comment);
		Comment createdComment = (Comment) create(DrupalCollection.Comment, c);
		comment.setCid(createdComment.getCid());
		return comment;
	}

	@Override
	public User createUser(User user) throws DrupalException {

		User createdUser = (User) create(DrupalCollection.User, user);
		user.setUid(createdUser.getUid());
		return user;

	}

	@Override
	public void createTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException {
		create(DrupalCollection.TaxonomyTerm, taxonomyTerm);
	}

	@Override
	public File createFile(File file) throws DrupalException {
		
		File createdFile = (File) create(DrupalCollection.File, file);
		
		file.setFid(createdFile.getFid());
		return file;
		
	}

	@Override
	public void createTaxonomyVocabulary(
			TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
		
		TaxonomyVocabularyRequest rtv=new TaxonomyVocabularyRequest();
		ArrayList<TaxonomyVocabulary> list=new ArrayList<TaxonomyVocabulary>();
		list.add(taxonomyVocabulary);
		rtv.setVocabulary(list);
		create(DrupalCollection.TaxonomyVocabulary, taxonomyVocabulary);
	}

	@Override
	public void logout() throws ConnectionException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", username);
		params.put("password", password);
		
		String request = GsonFactory.getGson().toJson(params);
		
		WebResource r;
		try {
			r = this.client.resource(new URI("http", null, server, port, apiUrl
					+"/"+DrupalCollection.User.getEndpoint() +"/" +LOGOUT, null, null));
		} catch (URISyntaxException e) {
			throw new ConnectionException(ConnectionExceptionCode.UNKNOWN,
					null, null);
		}

		ClientResponse response = r.accept(MediaType.APPLICATION_JSON_TYPE)
				.entity(request, MediaType.APPLICATION_JSON_TYPE)
				.cookie(sessionId)
				.post(ClientResponse.class);

		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			//Nothing to do here
		} else if (status == Status.UNAUTHORIZED) {
			throw new ConnectionException(
					ConnectionExceptionCode.INCORRECT_CREDENTIALS, null, null);
		} else {
			throw new ConnectionException(ConnectionExceptionCode.CANNOT_REACH,
					null, null);
		}
		client = null;
		sessionId = null;
	}

	@Override
	public boolean isConnected() {
		return client != null && sessionId!= null && sessionId.getValue() != null;
	}

	@Override
	public boolean deleteNode(int nodeId) throws DrupalException {
		boolean result = deleteOne(DrupalCollection.Node,nodeId);
		return result;
	}
	
	@Override
	public boolean deleteComment(int commentId) throws DrupalException {
		Boolean result = deleteOne(DrupalCollection.Comment,commentId);		
		return result;
	}
	
	/**
	 * Generic method to read entities from dynamic collections
	 * 
	 * @param collection
	 *            a member of the
	 *            {@link org.mule.modules.drupal.DrupalCollection} enum
	 *            representing the collection that owns the entity
	 * @param objectId
	 *            the id of the entity you want to get
	 * @return a subclass instance of
	 *         {@link org.mule.modules.drupal.model.DrupalEntity} with the
	 *         created entity
	 * @throws DrupalException
	 */
	private Boolean deleteOne(DrupalCollection collection, int objectId)
			throws DrupalException {
		Boolean response = this.executeDeleteRequest(
				this.getWebResource().path(collection.getEndpoint())
						.path(String.valueOf(objectId)), null,null);
		return response;
	}

	@Override
	public void deleteTaxonomyVocabulary(int taxonomyVocId) throws DrupalException {
		deleteOne(DrupalCollection.TaxonomyVocabulary,taxonomyVocId);
	}
	@Override
	public void deleteTaxonomyTerm(int taxonomyTermId) throws DrupalException{
		deleteOne(DrupalCollection.TaxonomyTerm,taxonomyTermId);
	}

	@Override
	public boolean deleteFile(int fileId) throws DrupalException {
		boolean result = deleteOne(DrupalCollection.File,fileId);
		return result;
	}

	@Override
	public boolean deleteUser(int userId) throws DrupalException {
		boolean result = deleteOne(DrupalCollection.User,userId);
		return result;
	}

	@Override
	public Node updateNode(Node node) throws DrupalException {
		
		node = (Node)update(DrupalCollection.Node, node.getNid().toString(), node);
		
		return node;
	}

	@Override
	public Comment updateComment(Comment comment) throws DrupalException {
		
		comment = (Comment)update(DrupalCollection.Comment, comment.getCid().toString(), comment);
		
		return comment;
	}

	@Override
	public User updateUser(User user) throws DrupalException {
		
		user = (User)update(DrupalCollection.User, user.getUid().toString(), user);
		
		return user;
	}

	@Override
	public TaxonomyTerm updateTaxonomyTerm(TaxonomyTerm taxonomyTerm) throws DrupalException {
		
		taxonomyTerm = (TaxonomyTerm)update(DrupalCollection.TaxonomyTerm, taxonomyTerm.getTid().toString(), taxonomyTerm);
		
		return taxonomyTerm;
	}

	@Override
	public TaxonomyVocabulary updateTaxonomyVocabulary(
			TaxonomyVocabulary taxonomyVocabulary) throws DrupalException {
		
		taxonomyVocabulary = (TaxonomyVocabulary)update(DrupalCollection.TaxonomyVocabulary, taxonomyVocabulary.getVid().toString(), taxonomyVocabulary);
		
		return taxonomyVocabulary;
	}

	@Override
	public String connectionId() {
		if(sessionId!=null)
			return this.sessionId.toString();
		return this.server;
	}

	@Override
	public int countAllComments(int nodeId) throws DrupalException {

		WebResource.Builder builder;
		
		builder = this.getWebResource().path(DrupalCollection.Comment.getEndpoint()).path(COUNT_ALL).accept(MediaType.APPLICATION_JSON_TYPE)
					.cookie(sessionId);

		CountRequest cr=new CountRequest();
		cr.setNodeId(nodeId);
		builder = builder.entity(GsonFactory.getGson().toJson(cr),
					MediaType.APPLICATION_JSON_TYPE);
		
		ClientResponse response = builder.method("POST", ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			String json = response.getEntity(String.class);
			int[] commentCount=GsonFactory.getGson().fromJson(json, int[].class);

			if( commentCount.length > 0 )
			return commentCount[0];
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected. Reason:%s",
					status.getStatusCode(),status.getReasonPhrase()));
		}
		return 0;
	}

	@Override
	public int countNewComments(int nodeId, int since) throws DrupalException {

		WebResource.Builder builder;

		builder = this.getWebResource().path(DrupalCollection.Comment.getEndpoint()).path(COUNT_NEW).accept(MediaType.APPLICATION_JSON_TYPE)
					.cookie(sessionId);
		CountRequest cr=new CountRequest();
		cr.setNodeId(nodeId);
		cr.setSince(since);
		builder = builder.entity(GsonFactory.getGson().toJson(cr),
					MediaType.APPLICATION_JSON_TYPE);
		
		ClientResponse response = builder.method("POST", ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			String json = response.getEntity(String.class);
			int[] commentCount=GsonFactory.getGson().fromJson(json, int[].class);
			if(commentCount.length > 0 )
				return commentCount[0];
			
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected. Reason:%s",
					status.getStatusCode(),status.getReasonPhrase()));
		}
		return 0;
	}

	@Override
	public User registerUser(User user) throws DrupalException {
		User createdUser = (User) this.executeRequest("POST",
				this.getWebResource().path(DrupalCollection.User.getEndpoint()).path(REGISTER)
				, DrupalCollection.User,user);
		user.setUid(createdUser.getUid());
		return user;
	}
	
	@Override
	public List<Node> indexNodes(List<String> fields,int startPage,int pagesize) throws DrupalException{
		List<Node> list=null;
		Type listType = new TypeToken<List<Node>>(){}.getType();
		list = index(DrupalCollection.Node,fields,startPage,pagesize,listType);
		return list;
	}
	
	@Override
	public List<Comment> indexComments(List<String> fields,int startPage,int pagesize) throws DrupalException{
		List<Comment> list=null;
		Type listType = new TypeToken<List<Comment>>(){}.getType();
		list = index(DrupalCollection.Comment,fields,startPage,pagesize,listType);
		return list;
	}
	
	@Override
	public List<User> indexUsers(List<String> fields,int startPage,int pagesize) throws DrupalException{
		List<User> list=null;
		Type listType = new TypeToken<List<User>>(){}.getType();
		list = index(DrupalCollection.User,fields,startPage,pagesize,listType);
		return list;
	}
	
	@Override
	public List<TaxonomyTerm> indexTaxonomyTerms(List<String> fields,int startPage,int pagesize) throws DrupalException{
		List<TaxonomyTerm> list=null;
		Type listType = new TypeToken<List<TaxonomyTerm>>(){}.getType();
		list = index(DrupalCollection.TaxonomyTerm,fields,startPage,pagesize,listType);
		return list;
	}
	
	@Override
	public List<TaxonomyVocabulary> indexTaxonomyVocabulary(List<String> fields,int startPage,int pagesize) throws DrupalException{
		List<TaxonomyVocabulary> list=null;
		Type listType = new TypeToken<List<TaxonomyVocabulary>>(){}.getType();
		list = index(DrupalCollection.TaxonomyVocabulary,fields,startPage,pagesize,listType);
		return list;
	}
	
	@Override
	public List<File> indexFiles(List<String> fields, int startPage,
			int pagesize) throws DrupalException {
		List<org.mule.modules.drupal.model.File> list=null;
		Type listType = new TypeToken<List<org.mule.modules.drupal.model.File>>(){}.getType();
		list = index(DrupalCollection.File,fields,startPage,pagesize,listType);
		return list;

	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> index(DrupalCollection collection,List<String> fields,int startPage,int pagesize,Type listType) throws DrupalException{
		
		WebResource wr=this.getWebResource().path(collection.getEndpoint());
		
		if( startPage>=0 ){
			wr = wr.queryParam("page", String.valueOf(startPage));
		}
		if( pagesize>0 ){
			wr = wr.queryParam("pagesize", String.valueOf(pagesize));
		}
		if( fields!= null && fields.size()>0){
			String requiredFields= StringUtils.join(fields.toArray(),",");
			wr = wr.queryParam("fields",requiredFields);
		}
		
		return (List<T>)(T)this.executeListRequest("GET",
				wr,listType,null);
	}

	@Override
	public List<Node> getNodesWithTerm(int termId) throws DrupalException{
		List<Node> list=null;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("tid", String.valueOf(termId));
		
		WebResource wr;
			
		wr = this.getWebResource().path(DrupalCollection.TaxonomyTerm.getEndpoint()).path(ACTION_SELECTNODES);
		
		Type listType = new TypeToken<List<Node>>(){}.getType();
	
		list = this.executeListRequest("POST", wr, listType,params);
			
		return list;
	}
	
	@Override
	public List<TaxonomyTerm> getTaxonomyVocabularyTree(int vocabularyId, int parent, int maxdepth) throws DrupalException{
		List<TaxonomyTerm> list=null;
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("vid", String.valueOf(vocabularyId));
		if(parent>=0)
			params.put("parent", String.valueOf(parent));
		if(maxdepth>0)
			params.put("maxdepth",String.valueOf(maxdepth));
		
		WebResource wr;
		
		wr = this.getWebResource().path(DrupalCollection.TaxonomyVocabulary.getEndpoint()).path(ACTION_GETTREE);
		
		Type listType = new TypeToken<List<TaxonomyTerm>>(){}.getType();
		
		list = this.executeListRequest("POST", wr, listType,params);
			
		return list;
	}

	@Override
	public List<Comment> getCommentsForNode(int nodeId) throws DrupalException {
		List<Comment> list=null;
	
		WebResource wr;
	
		wr = this.getWebResource().path(DrupalCollection.Node.getEndpoint()).path(String.valueOf(nodeId)).path(RELATIONSHIP_COMMENTS);
		
		Type listType = new TypeToken<Map<String,Comment>>(){}.getType();

		WebResource.Builder builder = wr.accept(MediaType.APPLICATION_JSON_TYPE)
				.cookie(sessionId);

		
		ClientResponse response = builder.method("GET", ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			
			String json = response.getEntity(String.class);
			Map<String,Comment> apiResponse = GsonFactory.getGson().fromJson(json,listType);
			list= new ArrayList<Comment>(apiResponse.values());
			
			return list;
			
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected",
					status.getStatusCode()));
		}
	}

	@Override
	public List<File> getFilesForNode(int nodeId) throws DrupalException {
		List<File> list=null;
		
		WebResource wr;
		
		wr = this.getWebResource().path(DrupalCollection.Node.getEndpoint()).path(String.valueOf(nodeId)).path(RELATIONSHIP_FILES);
			
		Type listType = new TypeToken<List<File>>(){}.getType();
			
		list = this.executeListRequest("GET", wr, listType,null);

		return list;
	}

	@Override
	public List<org.mule.modules.drupal.model.File> attachFilesToNode(List<java.io.File> files, int nodeId, String fieldName,
			boolean attach) throws DrupalException {
		
		List<File> list = null;
		
		WebResource wr = this.getWebResource().path(DrupalCollection.Node.getEndpoint()).path(String.valueOf(nodeId)).path(ACTION_ATTACHFILE);

		FormDataMultiPart multiPart = new FormDataMultiPart();

		for (int i = 0; i < files.size(); i++) {
			multiPart.bodyPart(new FileDataBodyPart("files["+(i+1)+"]", files.get(i), MediaType.APPLICATION_OCTET_STREAM_TYPE));
		}
		
		multiPart.field("nid", Integer.toString(nodeId));
		multiPart.field("field_name", fieldName);
		multiPart.field("attach", Boolean.toString(attach));

		Type listType = new TypeToken<List<File>>(){}.getType();
		
		WebResource.Builder builder = wr.type(MediaType.MULTIPART_FORM_DATA).entity(multiPart).accept(MediaType.APPLICATION_JSON_TYPE).cookie(sessionId);
		
		ClientResponse response = builder.method("POST", ClientResponse.class);
		Status status = response.getClientResponseStatus();

		if (status == Status.OK) {
			String json = response.getEntity(String.class);
			List<File> apiResponse = GsonFactory.getGson().fromJson(json,listType);
			list= new ArrayList<File>(apiResponse);
			
			return list;
			
		} else if (status == Status.UNAUTHORIZED) {
			throw new DrupalException("Drupal returned "
					+ status.getStatusCode());
		} else {
			throw new DrupalException(String.format(
					"API returned status code %d, 200 was expected",
					status.getStatusCode()));
		}
		
	}
}
