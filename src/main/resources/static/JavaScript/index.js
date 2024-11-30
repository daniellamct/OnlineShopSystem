
function printTypes(type) {
    const myProducts = document.getElementById('products');
    
    // Deleting payment list
    const myHead = document.getElementById('the_thead');
    myHead.innerHTML = '';
    const myBody = document.getElementById('the_tbody');
    myBody.innerHTML = '';
    const myConfirm = document.getElementById('payment-confirm-container');
    myConfirm.innerHTML = '';
    
    // Deleting current product detail's page
    const searching = document.getElementById('searching');
    searching.innerHTML = '';

    // Deleting all current products
    myProducts.innerHTML = ''; 
    
    //Changing the content
    for(const product of products) {
        if(((product.ProductCategory === type) || (type === 'all')) && (product.show)){
            const theItem = document.createElement('div');
            theItem.className = 'items';
            theItem.innerHTML = `
            <div class="name">${product.Name}</div>
            <img class="item-pic" src="${product.ImageSource}">
            <div class="action-number">
            <button class="number-btn decrease" onclick="update('${product.js_id}', -1)">-</button>
            <div class="number" id=${'total_Q_' + product.js_id}>${product.Quantity}</div>
            <button class="number-btn increase" onclick="update('${product.js_id}', 1)">+</button>
            </div>
            <div class='price'>${'each $' + product.RegularPrice}</div>        
            `;
            myProducts.appendChild(theItem);
            
        }
    }

    const type_text = document.getElementById(`type-word`);
    if(type === "all"){
        type_text.innerHTML = "All";
    }else if(type === "food"){
        type_text.innerHTML = "Food";
    }else if(type === "drinks"){
        type_text.innerHTML = "Drinks";
    }else if(type === "snacks"){
        type_text.innerHTML = "Snacks";
    }else{
        console.log("Error when setting the type of page.");
    }
}

let total_price = 0;

function update(targetId, target) {
    for(const product of products) {
        if(product.js_id === targetId){
            if((product.Quantity > 0) || (target == 1)){

                product.Quantity = (product.Quantity + target);
                
                const newQ = document.getElementById(`total_Q_${product.js_id}`);

                const total = document.getElementById(`payment-price`);
                
                total_price = total_price + (product.RegularPrice*target);
                total.innerHTML = `${'$' + total_price}`;

                if(newQ){
                    newQ.innerHTML = `${product.Quantity}`;
                
                }else{
                    console.log("cant find");
                }
            }
            break;
        }

    }
}

let my_total_price = 0;

function paymentPage() {
  console.log("preparing payment");

  // Deleting all current products
  const myProducts = document.getElementById('products');
  myProducts.innerHTML = ''; 
  const type_text = document.getElementById(`type-word`);
  type_text.innerHTML = '';

  // Deleting current products's details
  const searching = document.getElementById('searching');
  searching.innerHTML = '';

  // Adding the head of table
  const myHead = document.getElementById('the_thead');
  myHead.innerHTML = '';
 
  const theTitle = document.createElement('tr');
  theTitle.innerHTML = `
  <th>Product Name</th>
  <th>Quantity</th>
  <th>Total Price</th>
  `;

  myHead.appendChild(theTitle);


  // Adding the body of table
  const myBody = document.getElementById('the_tbody');
  myBody.innerHTML = '';
  
  for(const product of products) {
      if(product.Quantity > 0) {
          const theItem = document.createElement('tr');
          theItem.innerHTML = `
          <td>${product.Name}</td>
          <td>${product.Quantity}</td>
          <td>${product.RegularPrice*product.Quantity}</td>
          `;
          myBody.appendChild(theItem);
          my_total_price = my_total_price + product.RegularPrice*product.Quantity;
      }
  }

  // Adding confirm words
  const myConfirm = document.getElementById('payment-confirm-container');
  myConfirm.innerHTML = `
  Total Cost: $ ${my_total_price} <button onclick="sendTransactionData()"> Confirm Payment</button>
  `;


}

function searching(){
    // Get the value from the input field
    const targetName = document.getElementById('searchInput').value;

    // finding the target product
    let targetProduct = null;
    for(const product of products){
        if(product.Name == targetName){
            targetProduct = product;
            break;
        }
    }
    if(targetProduct == null){
        console.log("product not found");
        return;
    }
    console.log("product found");

    if(!targetProduct.show){
        return;
    }
    // Show the product details page

    // Deleting all current products
    const myProducts = document.getElementById('products');
    myProducts.innerHTML = ''; 
    const type_text = document.getElementById(`type-word`);
    type_text.innerHTML = '';

    // Deleting payment list
    const myHead = document.getElementById('the_thead');
    myHead.innerHTML = '';
    const myBody = document.getElementById('the_tbody');
    myBody.innerHTML = '';
    const myConfirm = document.getElementById('payment-confirm-container');
    myConfirm.innerHTML = '';


    // Showing the details
    const searching = document.getElementById('searching');

    // Deleting current products's details
    searching.innerHTML = '';

    const theItem = document.createElement('div');
    theItem.className = 'searchingView';
    theItem.innerHTML = `
    <div id="name">${targetProduct.Name}</div>
    <img id="item-pic" src="${targetProduct.ImageSource}">
    <div id="type">Type: ${targetProduct.ProductCategory}</div>
    <div id="RPrice"> Regular Price: ${targetProduct.RegularPrice}</div>
    <div id="description"> Description: ${targetProduct.description}</div>
    <div id="keywords">Keywords: ${targetProduct.Keywords[0]}, ${targetProduct.Keywords[1]}, ${targetProduct.Keywords[2]}</div>    
    `;
    searching.appendChild(theItem);

}

// Function to send data to the backend
function sendTransactionData() {

    if(my_total_price == 0){
        return;
    }

    const calculatedData = {
    username: usernamePassing,
    totalPrice: my_total_price, 

    };
    console.log("send transaction is clicked")
    console.log(calculatedData); // This will update the data

    fetch('http://localhost:8080/api/transactions', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(calculatedData), // Convert the data to JSON
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Transaction saved successfully:', data);
    })
    .catch((error) => {
        console.error('Error saving transaction:', error);
    });

    window.location.href = window.location.origin + '/';
}



// When frontend script is loaded
function initialize() {
  printTypes('all');
  }
document.addEventListener('DOMContentLoaded', initialize);


