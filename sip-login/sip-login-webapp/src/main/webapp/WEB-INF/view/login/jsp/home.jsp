<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Stock Ticker</title>
</head>
<body>
  <h1>Stock Ticker</h1>

  <table>
    <thead><tr><th>Code</th><th>Price</th><th>Time</th></tr></thead>
    <tbody id="price"></tbody>
  </table>

  <p class="new">
    Code: <input type="text" class="code"/>
    Price: <input type="text" class="price"/>
    <button class="add">Add</button>
    <button class="remove-all">Remove All</button>
  </p>

  <script src="${pageContext.request.contextPath}/js/sockjs-0.3.4.js"></script>
  <script src="${pageContext.request.contextPath}/js/stomp.js"></script>
  <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
  <script>
    //Create stomp client over sockJS protocol
    var socket = new SockJS("/cas/ws");
    var stompClient = Stomp.over(socket);

    // Render price data from server into HTML, registered as callback
    // when subscribing to price topic
    function renderPrice(frame) {
      var prices = JSON.parse(frame.body);
      $('#price').empty();
      for(var i in prices) {
        var price = prices[i];
        $('#price').append(
          $('<tr>').append(
            $('<td>').html(price.code),
            $('<td>').html(price.price.toFixed(2)),
            $('<td>').html(price.timeStr)
          )
        );
      }
    }
    
    // Callback function to be called when stomp client is connected to server
    var connectCallback = function() {
      stompClient.subscribe('/topic/price', renderPrice);
    }; 

    // Callback function to be called when stomp client could not connect to server
    var errorCallback = function(error) {
      alert(error.headers.message);
    };

    // Connect to server via websocket
    stompClient.connect("guest", "guest", connectCallback, errorCallback);
    
    // Register handler for add button
    $(document).ready(function() {
      $('.add').click(function(e){
        e.preventDefault();
        var code = $('.new .code').val();
        var price = Number($('.new .price').val());
        var jsonstr = JSON.stringify({ 'code': code, 'price': price });
        stompClient.send("/app/addStock", {}, jsonstr);
        return false;
      });
    });
    
    // Register handler for remove all button
    $(document).ready(function() {
      $('.remove-all').click(function(e) {
        e.preventDefault();
        stompClient.send("/app/removeAllStocks");
        return false;
      });
    });
  </script>
</body>
</html>
