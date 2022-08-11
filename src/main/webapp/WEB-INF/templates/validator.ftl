<html>

<head>
    <title>Credit card validator</title>
    <link href="/style" rel="stylesheet">
</head>

<body>
<form action="/cards/validator" method="POST">
    <div class="container">
        <div class="card">
            <div class="top">
                <h3>Add new card</h3>
                <hr>
            </div>
            <div class="card-details"> <input name="cardNumber" type="text" placeholder="0000 0000 0000 0000" data-slots="0" data-accept="\d" size="19">
                <span>Card Number</span> <i class="fa fa-credit-card"></i> </div>
            <div class="exp-cvv">
                <div class="card-details"> <input name="expiryDate" type="text" placeholder="mm/yyyy" data-slots="my">
                    <span>Expiry date</span> <i class="fa fa-calendar"></i> </div>
                <div class="card-details"> <input name="cvvCode" type="text" placeholder="0000" data-slots="0" data-accept="\d" size="4">
                    <span>CVV</span> <i class="fa fa-info-circle"></i> </div>
            </div>
            <div class="button" typeof="submit"> <button>Check entries</button> </div>
        </div>
    </div>
</form>
</body>

</html>