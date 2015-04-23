function setElement(jsVar) {
    window[jsVar + '_element'] = window[jsVar].element;
}

function append(divId, jsVar) {
    document.getElementById(divId).appendChild(window[jsVar + '_element']);
}