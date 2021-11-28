import React, {useState} from "react";
import ReactDOM from 'react-dom';
import '../css/main.css'
import axios from "axios";
import {Button} from "@material-ui/core";

function Main() {
    const [result, setResult] = useState(null);
    const [filename, setFilename] = useState("");

    const onFileUpload = () => {
        console.log("filename into onFileUpload: ", filename);
        axios.post(`http://localhost:8080/api/sca`, filename,
            {
                headers: {
                    'Content-Type': 'application/json; charset=UTF-8'
                }
            }).then(res => {
            console.log('res: ', res);
            setResult(res.data);
        });
    }
    console.log('result after setState: ', result);
    return (
        <div>
            <h1>Введите путь к файлу для статического анализа кода:</h1>
            <input style={{width: "40%"}}
                   type="text"
                   onChange={(event) => setFilename(event.target.value)}
            />
            <br/>
            <Button color="primary"
                    style={{backgroundColor: "lightgray"}}
                    onClick={() => onFileUpload(filename)}>
                Выполнить анализ
            </Button>
        </div>
    );
}

ReactDOM.render(
    <Main/>,
    document.getElementById('react-mountpoint')
);
