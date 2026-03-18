import { useState,useEffect } from "react"
import axios from "axios"
import {
LineChart,
Line,
XAxis,
YAxis,
CartesianGrid,
Tooltip,
ResponsiveContainer
} from "recharts"
import "./App.css"

interface URL{

id:number
originalUrl:string
shortCode:string
createdAt:string
clickCount:number

}

function App(){

const [input,setInput]=useState("")
const [urls,setUrls]=useState<URL[]>([])
const [selected,setSelected]=useState<URL | null>(null)
const chartData = selected ? [

{ name:"Day 1", clicks:0 },

{ name:"Day 2", clicks:Math.floor(selected.clickCount*0.3) },

{ name:"Day 3", clicks:Math.floor(selected.clickCount*0.6) },

{ name:"Day 4", clicks:selected.clickCount }

] : []

const API="http://localhost:8080/api/urls"

const loadUrls=async()=>{

try{

const res=await axios.get(API)

setUrls(res.data)

}catch(error){

console.log(error)

}

}

useEffect(()=>{

loadUrls()

},[])

const createShort=async()=>{

if(input===""){

alert("Enter URL")

return

}

await axios.post(API,{
originalUrl:input
})

setInput("")

loadUrls()

}

const viewAnalytics=(url:URL)=>{

setSelected(url)

}

return(

<div className="container">

<header className="header">

<h1>Easy URL Shortener</h1>

</header>

<section className="hero">

<h2>Simplify your URL</h2>

<div className="inputArea">

<input

value={input}

onChange={(e)=>setInput(e.target.value)}

placeholder="Enter long URL"

className="input"

/>

<button
onClick={createShort}
className="button"
>

Shorten URL

</button>

</div>

<p className="helper">

Paste a long URL to generate a short URL

</p>

</section>

<section>

<h2>Recent URLs</h2>

<table className="table">

<thead>

<tr>

<th>Original URL</th>
<th>Short URL</th>
<th>Created</th>
<th>Clicks</th>
<th>Analytics</th>

</tr>

</thead>

<tbody>

{urls.map((u)=>(

<tr key={u.id}>

<td>{u.originalUrl}</td>

<td>

<a

className="link"

href={"http://localhost:8080/api/urls/"+u.shortCode}

target="_blank"

>

{u.shortCode}

</a>

</td>

<td>

{new Date(u.createdAt).toLocaleString()}

</td>

<td>

{u.clickCount}

</td>

<td>

<button

className="analyticsBtn"

onClick={()=>viewAnalytics(u)}

>

View

</button>

</td>

</tr>

))}

</tbody>

</table>

</section>

{selected && (

<section className="analytics">

<h3>Click Statistics</h3>

<ResponsiveContainer width="100%" height={300}>

<LineChart data={chartData}>

<CartesianGrid strokeDasharray="3 3"/>

<XAxis dataKey="name"/>

<YAxis/>

<Tooltip/>

<Line
type="monotone"
dataKey="clicks"
stroke="#1976d2"
strokeWidth={3}
dot={{r:6}}
/>

</LineChart>

</ResponsiveContainer>

</section>

)}

</div>

)

}

export default App