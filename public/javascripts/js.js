let conts = ["Asia", "Africa", "Europe", "North America", "South America", "Australia", "Antarctica"];

//Sorted Continents in ascending order
let sortedconts = conts.sort();
console.log(sortedconts);

//referenc
let input = document.getElementById("continent");

//Execute function on keyup
input.addEventListener("keyup", (e) => {
    //Initially remove all elements (so if user erases a letter or adds new letter then clean previous outputs)
    removeElements();
    //loop through above array
    for(let i of sortedconts)
    {
        //convert input to lowercase and compare with each string
        if(i.toLowerCase().startsWith(input.value.toLowerCase()) && input.value != "")
        {
            //create li element
            let listItem = document.createElement("li");

            //One common class name
            listItem.classList.add("list-items");
            listItem.style.cursor - "pointer";
            listItem.setAttribute("onclick", "displayConts('" + i + "')");

            //Display matched part in bold
            let word = "<b>" + i.substr(0,input.value.length) + "</b>";
            word += i.substr(input.value.length);

            //display the value in array
            listItem.innerHTML = word;
            document.querySelector(".list").appendChild(listItem);
        }
    }
});

function displayConts(value)
{
    input.value = value;
    removeElements();
}

function removeElements()
{
    //clear all the items
    let items = document.querySelectorAll(".list-items");
    items.forEach((items) => {
        items.remove();
    });
}