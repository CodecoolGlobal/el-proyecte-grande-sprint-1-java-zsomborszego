import SelectIngredient from "../SelectIngredient";
import {useState} from "react";

function CreateRecipe() {

    const [selectList, editList] = useState([<SelectIngredient/>])
    const listItems = selectList.map((item, index) =>
        <div key={index}>      {item}
        </div>
    );
    function add(){
        editList(prevArray => [...prevArray, <SelectIngredient/>])
    }

    return (<div className="add-ingredient-container">
        <div className="add-ingredient-baackround">
            <form action="http://localhost:8080/api/add-reciept" method="POST">
                <label className="label-text">
                    Reciept Name
                    <input className="label-input" type="text" name="name" required/>
                </label>
                <label className="label-text">
                    Price
                    <input className="label-input" type="number" name="name" required/>
                </label>
                <div>
                    {listItems}
                    <button type="button" onClick={add}>+</button>
                </div>
                <button className="submit-button" type="submit">Save me</button>
            </form>
        </div>
    </div>)
}

export default CreateRecipe;