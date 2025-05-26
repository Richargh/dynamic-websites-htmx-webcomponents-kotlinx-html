package de.richargh.sandbox.htmx.kotlinxhtml.commons.widgets.web

import kotlinx.html.*

open class NATIVE_MODAL(
    initialAttributes : Map<String, String>,
    override val consumer : TagConsumer<*>
) : HTMLTag("native-modal", consumer, initialAttributes, null, false, false), HtmlBlockTag

inline fun FlowContent.nativeModal(
    classes : String? = null,
    crossinline block : NATIVE_MODAL.() -> Unit = {}
) : Unit = NATIVE_MODAL(attributesMapOf("class", classes), consumer).visit(block)


const val nativeModalFileName = "native-modal.js"

// language=JavaScript
const val nativeModalCode = """
customElements.define("native-modal", class extends HTMLElement {
    connectedCallback() {
        const dialog = this.querySelector('dialog');
        const form = dialog.querySelector('form');
        
        form.addEventListener("submit", (event) => {
            dialog.close();
        });
        
        form.addEventListener("reset", (event) => {
            dialog.close();
        });
        
        dialog.showModal();
    }
});
"""