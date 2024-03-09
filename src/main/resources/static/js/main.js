'use strict';

var stompClient = null;

//----------------------

function click(event){ // button
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onClickButton, onError);
    event.preventDefault();
}

function onClickButton(){ // button
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/componentInteracted", {}, JSON.stringify({type:'click', message: 'clicked in the button'})); // payload do controller
}

function clickTextBox(event){ // textbox
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onClickTextBox, onError);
    event.preventDefault();
}

function onClickTextBox(){ // textbox
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/componentInteracted", {}, JSON.stringify({type: 'click', message: 'clicked in the textbox'}))
}

function checkCheckBox(event){ // checkbox
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onCheckCheckBox, onError);
    event.preventDefault();
}

function onCheckCheckBox(){ // checkbox
    var checkbox = document.getElementById("messageCheckbox");
    var isChecked = checkbox.checked;
    if (isChecked) {
        stompClient.subscribe('/topic/public');
        stompClient.send("/app/componentInteracted", {}, JSON.stringify({type: 'check', message: 'checked the checkbox'}))
    }
    else{
        stompClient.subscribe('/topic/public');
        stompClient.send("/app/componentInteracted", {}, JSON.stringify({type: 'uncheck', message: 'unchecked the checkbox'}))
    }
}

function changeSelectItem(event){ // select (combobox)
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onChangeSelectItem, onError);
    event.preventDefault();
}

function onChangeSelectItem(){ // select (combobox)
    var select = document.getElementById("messageSelect");
    var selectedItem = select.value;
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/componentInteracted", {}, JSON.stringify({type: 'change', message: `changed the select item to ${selectedItem}`}))
}

function mouseHoverLabel(event){
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onMouseHoverLabel, onError);
    event.preventDefault();
}

function onMouseHoverLabel(){
    stompClient.subscribe('/topic/public');
    stompClient.send("/app/componentInteracted", {}, JSON.stringify({type: 'mouse on', message: 'hovered the mouse over the label'}))
}

//-------------------

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

///-------------

messageButton.addEventListener('click', click, true)
messageCheckbox.addEventListener('change', checkCheckBox, true)
messageSelect.addEventListener('change', changeSelectItem, true)
messageLabel.addEventListener('mouseenter', mouseHoverLabel, true)


