package nuxeo.operations;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.core.api.NuxeoPrincipal;
import org.nuxeo.ecm.platform.comment.api.CommentManager;
import org.nuxeo.runtime.api.Framework;

/**
 *
 */
@Operation(id=AddComment.ID, category=Constants.CAT_DOCUMENT, label="Ajout d'un commentaire sur un document", description="Describe here what your operation does.")
public class AddComment {

    public static final String ID = "Document.AddComment";

    @Context
    protected CoreSession session;

    @Param(name = "comment", required = true)
    protected String comment;

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
    	CommentManager commentManager = Framework.getService(CommentManager.class);
    	NuxeoPrincipal user = (NuxeoPrincipal) session.getPrincipal();
    	commentManager.createComment(doc, comment, user.getName());
    	return doc;
    }
    
    @OperationMethod
    public DocumentModelList run(DocumentModelList docs) {
    	CommentManager commentManager = Framework.getService(CommentManager.class);
    	NuxeoPrincipal user = (NuxeoPrincipal) session.getPrincipal();
    	
    	for (DocumentModel doc : docs) {
        	commentManager.createComment(doc, comment, user.getName());
        }
    	
    	return docs;
    }
}
