import React, {useState} from "react";
import ReactDOM from 'react-dom';
import '../css/main.css'
import axios from "axios";
import {Button} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import TableContainer from "@material-ui/core/TableContainer";
import Table from "@material-ui/core/Table";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";

function Main() {
    const [result, setResult] = useState(null);
    const [filename, setFilename] = useState("");

    const onFileUpload = () => {
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

    const isResultEmpty = (result) => {
        return Object.keys(result).length === 0;
    }

    return (
        <>
            <h1>Введите путь к файлу для статического анализа кода:</h1>
            {/*<input style={{width: "40%"}}*/}
            {/*       type="text"*/}
            {/*       onChange={(event) => setFilename(event.target.value)}*/}
            {/*/>*/}
            {/*<br/>*/}

            <Grid container spacing={2}>
                <Grid item xs={10}>
                    <TextField id="outlined-basic"
                               label="Путь к файлу"
                               variant="outlined"
                               style={{width: "100%"}}
                               onChange={(event) => {
                                   console.log("event.target.value: ", event.target.value);
                                   setFilename(event.target.value)
                               }}
                    />
                    {console.log("filename: ", filename)}
                </Grid>
                <Grid item xs={2}>
                    <Button variant="contained"
                            onClick={() => onFileUpload(filename)}>
                        Выполнить анализ
                    </Button>
                </Grid>
                {console.log("result: ", result)}
                <Grid item xs={12}>
                    {result && !isResultEmpty(result) ?
                        <TableContainer component={Paper}>
                            <Table aria-label="simpler table">
                                <TableHead style={{backgroundColor: "#d5d5d5"}}>
                                    <TableRow>
                                        <TableCell align="center">Суть характеристики</TableCell>
                                        <TableCell align="center">Значение нормы</TableCell>
                                        <TableCell align="center">Соответстие норме</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow>
                                        <TableCell align="center">1. Максимальное количество строк в классе</TableCell>
                                        <TableCell align="center">{result.MAX_CLASS_LENGTH.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_CLASS_LENGTH.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">2. Максимальное количество строк в методе</TableCell>
                                        <TableCell align="center">{result.MAX_BODY_LENGTH.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_BODY_LENGTH.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">3. Максимальная длина имени метода</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_METHOD_NAME_LENGTH.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_METHOD_NAME_LENGTH.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">4. Максимальное количество параметров в
                                            методе</TableCell>
                                        <TableCell align="center">{result.MAX_PARAM_COUNT.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_PARAM_COUNT.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">5. Максимальная длина наименования параметров метода,
                                            полей класса</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_VARIABLE_LENGTH.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_VARIABLE_LENGTH.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">6. Максимальное количество полей в классе</TableCell>
                                        <TableCell align="center">{result.MAX_VARIABLE_COUNT.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_VARIABLE_COUNT.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">7. Максимальное количество методов в
                                            классе</TableCell>
                                        <TableCell align="center">{result.MAX_METHODS_COUNT.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_METHODS_COUNT.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">8. Минимальная длина наименования полей в классе,
                                            параметров в методе</TableCell>
                                        <TableCell
                                            align="center">{result.MIN_VARIABLE_LENGTH.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MIN_VARIABLE_LENGTH.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">9. Названия логических методов начинаются с приставки
                                            is,
                                            has</TableCell>
                                        <TableCell
                                            align="center">{result.BOOLEAN_STARTS_WITH_IS_HAS.thresholdValue ? "Да" : "Нет"}</TableCell>
                                        <TableCell
                                            align="center">{result.BOOLEAN_STARTS_WITH_IS_HAS.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">10. Наименования классов, методов, параметров, полей
                                            указано в CamelCase стиле, коснтанты в UPPER_CASE</TableCell>
                                        <TableCell
                                            align="center">{result.CAMEL_CASE_CLASS_NAME.thresholdValue ? "Да" : "Нет"}</TableCell>
                                        <TableCell
                                            align="center">{result.CAMEL_CASE_CLASS_NAME.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                        {/*    todo: объединить ворнинги по проверке на camelCase методов, параметров, полей и тд*/}
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">11. Максимальное количество символов в строке
                                            кода</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_SYMBOLS_COUNT_PER_LINE.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_SYMBOLS_COUNT_PER_LINE.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">12. Максимальное количество действий в одной
                                            строке</TableCell>
                                        <TableCell
                                            align="center">{result.ONE_ACTION_PER_LINE.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.ONE_ACTION_PER_LINE.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                    <TableRow>
                                        <TableCell align="center">12. Максимальное количество пустых строк для акцента
                                            внимания внутри классов</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_EMPTY_LINES_COUNT_PER_METHOD.thresholdValue}</TableCell>
                                        <TableCell
                                            align="center">{result.MAX_EMPTY_LINES_COUNT_PER_METHOD.warnings.length === 0 ? "Соответствует" : "Не соответствует"}</TableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </TableContainer>
                        : console.log("There are no data!")
                    }
                </Grid>
            </Grid>
        </>
    );
}

ReactDOM.render(
    <Main/>,
    document.getElementById('react-mountpoint')
);
