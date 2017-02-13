/**
 * Copyright 2009-2012 the original author or authors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.springframework.extensions.jcr.jackrabbit.ocm;


import javax.jcr.NamespaceException;
import javax.jcr.NamespaceRegistry;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Workspace;

import org.apache.jackrabbit.core.nodetype.NodeTypeManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.extensions.jcr.JcrSessionFactory;
import org.springframework.util.ObjectUtils;

/**
 * JCR session factory specific to Jackrabbit OCM.
 * 
 * @author <a href="mailto:christophe.lombart@sword-technologies.com">Christophe
 *         Lombart</a>
 */
public class OcmJackrabbitSessionFactory extends JcrSessionFactory {

	/*
	 * private Resource nodeTypes2Import;
	 * 
	 * public Resource getNodeTypes2Import() { return nodeTypes2Import; }
	 * 
	 * public void setNodeTypes2Import(Resource nodeTypes2Import) {
	 * this.nodeTypes2Import = nodeTypes2Import; }
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(OcmJackrabbitSessionFactory.class);

	private static final String DEFAULT_CONTENT_TYPE = "text/xml";

	/**
	 * Node definitions in CND format.
	 */
	private Resource[] nodeDefinitions;

	private String contentType = DEFAULT_CONTENT_TYPE;


	public void setNodeDefinitions(Resource[] nodeDefinitions) {
		this.nodeDefinitions = nodeDefinitions;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Register the namespaces.
	 * 
	 * @throws RepositoryException
	 */
	@Override
	protected void registerNamespaces() throws Exception {
		NamespaceRegistry registry = getSession().getWorkspace()
				.getNamespaceRegistry();

		int n = 0;
		String prefix = null;
		while (prefix == null) {
			try {
				prefix = registry.getPrefix(RepositoryUtil.OCM_NAMESPACE);
			} catch (NamespaceException e1) {
				// No, try to register it with the default prefix
				prefix = RepositoryUtil.OCM_NAMESPACE_PREFIX;
				// ... and a sequence number if the first attempt failed
				if (n++ > 0) {
					prefix = prefix + n;
				}
				try {
					// Is this prefix registered to the Graffito namespace?
					if (!RepositoryUtil.OCM_NAMESPACE.equals(registry
							.getURI(prefix))) {
						// No, but it *is* registered. Try the next prefix...
						prefix = null;
					}
				} catch (NamespaceException e2) {
					try {
						// No, and it's not registered. Try registering it:
						registry.registerNamespace(prefix,
								RepositoryUtil.OCM_NAMESPACE);
					} catch (NamespaceException e3) {
						// Registration failed. Try the next prefix...
						prefix = null;
					}
				}
			}
		}

		super.registerNamespaces();
	}

	@Override
	protected void registerNodeTypes() throws Exception {
		if (!ObjectUtils.isEmpty(nodeDefinitions)) {

			Session session = getBareSession();
			Workspace ws = session.getWorkspace();

			NodeTypeManagerImpl jackrabbitNodeTypeManager = (NodeTypeManagerImpl) ws
					.getNodeTypeManager();

			boolean debug = LOG.isDebugEnabled();
			for (int i = 0; i < nodeDefinitions.length; i++) {
				Resource resource = nodeDefinitions[i];
				if (debug) {
					LOG.debug("adding node type definitions from "
							+ resource.getDescription());
				}
				try {
					// ws.getNodeTypeManager().registerNodeType(ntd,
					// allowUpdate)
					jackrabbitNodeTypeManager.registerNodeTypes(
							resource.getInputStream(), contentType,true);
				} catch (RepositoryException ex) {
					LOG.error("Error registering nodetypes ", ex.getCause());
				}
			}
			session.logout();
		}
	}

	/*
	 * @Override protected void registerNodeTypes() throws Exception { if
	 * (nodeTypes2Import == null) return; InputStream xml =
	 * nodeTypes2Import.getInputStream();
	 * 
	 * // HINT: throws InvalidNodeTypeDefException, IOException
	 * QNodeTypeDefinition[] types = NodeTypeReader.read(xml);
	 * 
	 * Workspace workspace = getSession().getWorkspace(); NodeTypeManager ntMgr
	 * = workspace.getNodeTypeManager(); NodeTypeRegistry ntReg =
	 * ((NodeTypeManagerImpl) ntMgr).getNodeTypeRegistry();
	 * 
	 * for (int j = 0; j < types.length; j++) { QNodeTypeDefinition def =
	 * types[j];
	 * 
	 * try { ntReg.getNodeTypeDef(def.getName()); } catch
	 * (NoSuchNodeTypeException nsne) { // HINT: if not already registered than
	 * register custom node type ntReg.registerNodeType(def); }
	 * 
	 * } }
	 */

}
