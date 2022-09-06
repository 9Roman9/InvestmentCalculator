//Determine annuity
const annuity = document.querySelector('#annuityType');
let payment = document.querySelector('#cashFlowType');
function showAnnuity(){
    payment.innerHTML = ``;
    payment = document.querySelector('#cashFlowType');
    payment.innerHTML = `<label for="annuity">Сума ануїтету </label><input id="annuity" name="annuity" type="text"><br><br>`;
}
annuity.onclick = showAnnuity;

//Determine different cash flows
const different = document.querySelector('#differentType');
function showDifferentType(){
    payment.innerHTML = `<div id="cashFlowOne"><span id="periodDiff"></span></div>`;
    payment = document.querySelector('#cashFlowPeriods');
    let newPayment;
    for(let i = 1; i <= document.querySelector('#period').value; i++){

        if (i == 1) {
              newPayment = document.querySelector('#cashFlowOne');
              newPayment.innerHTML = `<label for="diffCashFlow">Сума доходу за період <span class="periodDiff">1: </span></label><input class="diffCashFlow" name="differentCashFlow" type="text"><br><br>`;
            } else {
              let np = newPayment.cloneNode(true);
              np.id = i;
              np.querySelector(".periodDiff").innerHTML = i + ": ";
              payment.append(np);
            }
    }
}
different.onclick = showDifferentType;

//Determine discount rate
let customDiscountRate = document.querySelector('#customDiscountRate');
let customDiscountRateInput = document.querySelector('#discountRate');
let bankDiscountRate = document.querySelector('#discountRateBank');
let newDiv = document.createElement('div');

function showInputRate(){
    newDiv.innerHTML = `<br><label for="inputRate">Власна ставка дисконтування: </label><input id="inputRate" name="customDiscountRate" type="text">`;
    customDiscountRateInput.append(newDiv);
}

function removeInputRate(){
    newDiv.innerHTML = ``;
    customDiscountRateInput.append(newDiv);
}

customDiscountRate.onclick = showInputRate;
bankDiscountRate.onclick = removeInputRate;

//Determine url with name of project
let form = document.querySelector('#form_update');
let submit = document.querySelector("button");

function setURL(){
    form.action = "/investor/update/" + document.querySelector("#name").value;
}

submit.onclick = setURL;