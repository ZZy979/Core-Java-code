@SuppressWarnings("module")
module v2ch09.openpkg2 {
    requires jakarta.json.bind;
    requires org.eclipse.yasson;
    opens com.horstmann.places to org.eclipse.yasson;
}
