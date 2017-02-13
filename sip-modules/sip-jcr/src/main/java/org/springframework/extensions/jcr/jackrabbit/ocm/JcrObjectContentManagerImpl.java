/**
 * 
 */
package org.springframework.extensions.jcr.jackrabbit.ocm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.QueryResult;

import org.apache.jackrabbit.ocm.exception.ObjectContentManagerException;
import org.apache.jackrabbit.ocm.manager.cache.ObjectCache;
import org.apache.jackrabbit.ocm.manager.impl.ObjectContentManagerImpl;
import org.apache.jackrabbit.ocm.manager.objectconverter.ObjectConverter;
import org.apache.jackrabbit.ocm.mapper.Mapper;
import org.apache.jackrabbit.ocm.query.Query;
import org.apache.jackrabbit.ocm.query.QueryManager;

/**
 * @author Administrator
 *
 */
public class JcrObjectContentManagerImpl extends ObjectContentManagerImpl {

	public JcrObjectContentManagerImpl(Mapper mapper,
			ObjectConverter converter, QueryManager queryManager,
			ObjectCache requestObjectCache, Session session) {
		super(mapper, converter, queryManager, requestObjectCache, session);
	}
	
	public JcrObjectContentManagerImpl(Session session,
			InputStream[] xmlMappingFiles) {
		super(session, xmlMappingFiles);
	}

	public JcrObjectContentManagerImpl(Session session, Mapper mapper) {
		super(session, mapper);
	}

	public JcrObjectContentManagerImpl(Session session, String[] xmlMappingFiles) {
		super(session, xmlMappingFiles);
	}

	public Collection getObjects(Query query,long limit,long offset) {
        try {
        	String jcrExpression = this.queryManager.buildJCRExpression(query);
            NodeIterator nodeIterator = getNodeIterator(jcrExpression,  javax.jcr.query.Query.XPATH,limit,offset);

            List result = new ArrayList();
            while (nodeIterator.hasNext()) {
                Node node = nodeIterator.nextNode();
                result.add(objectConverter.getObject(session, node.getPath()));
            }
            requestObjectCache.clear();
            return result;
        } catch (InvalidQueryException iqe) {
            throw new org.apache.jackrabbit.ocm.exception.RepositoryException("Invalid query expression", iqe);
        } catch (RepositoryException e) {
            throw new org.apache.jackrabbit.ocm.exception.RepositoryException("Impossible to get the object collection", e);
        }
    }
	
	private NodeIterator getNodeIterator(String query, String language,long limit,long offset) {
	        javax.jcr.query.Query jcrQuery;
	        try {
	            jcrQuery = session.getWorkspace().getQueryManager().createQuery(query, language);
	            jcrQuery.setLimit(limit);
	            jcrQuery.setOffset(offset);
	            QueryResult queryResult = jcrQuery.execute();
	            NodeIterator nodeIterator = queryResult.getNodes();
	            return nodeIterator;
	        } catch (InvalidQueryException iqe) {
	            throw new org.apache.jackrabbit.ocm.exception.InvalidQueryException(iqe);
	        } catch (RepositoryException re) {
	            throw new ObjectContentManagerException(re.getMessage(), re);
	        }
	    }

}
