package domOld;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * This tree model describes the tree structure of an XML document.
 */
public class DOMTreeModel implements TreeModel {
    private Document doc;

    /**
     * Constructs a document tree model.
     * @param doc the document
     */
    public DOMTreeModel(Document doc) {
        this.doc = doc;
    }

    @Override
    public Object getRoot() {
        return doc.getDocumentElement();
    }

    @Override
    public int getChildCount(Object parent) {
        return ((Node) parent).getChildNodes().getLength();
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((Node) parent).getChildNodes().item(index);
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node) parent;
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
            if (list.item(i) == child)
                return i;
        return -1;
    }

    @Override
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {}

    @Override
    public void addTreeModelListener(TreeModelListener l) {}

    @Override
    public void removeTreeModelListener(TreeModelListener l) {}
}
