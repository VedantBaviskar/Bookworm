function Searchbox(props){
return <input type="text" id={props.search} placeholder={props.placeholder} onChange={props.onchange}></input>
}
export default Searchbox