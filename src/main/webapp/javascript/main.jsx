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
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import makeStyles from "@material-ui/core/styles/makeStyles";

const useStyles = makeStyles(theme => ({
    root: {
        textAlign: "center",
    },
}));

function Main() {
    const [result, setResult] = useState(null);
    const [filename, setFilename] = useState("");
    const classes = useStyles();

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

    const printWarnings = (warnings) => {
        return <List>
            {warnings.map((warning, key) =>
                <ListItem key={key} className={classes.root}>
                    <ListItemText>{warning}</ListItemText>
                </ListItem>)}
        </List>
    }

    const getTableRow = (paramResult) => {
        return <TableRow>
            <TableCell align="center">{paramResult.paramNameRus}</TableCell>
            <TableCell align="center">{paramResult.thresholdValue}</TableCell>
            <TableCell align="center">
                {paramResult.warnings.length === 0
                    ? "Соответствует"
                    : printWarnings(paramResult.warnings)}
            </TableCell>
        </TableRow>
    }

    return (
        <>
            <h1>Введите путь к файлу для анализа программного кода:</h1>
            <Grid container spacing={2} alignItems="center" alignContent="center">
                <Grid item xs={10}>
                    <TextField id="outlined-basic"
                               label="Путь к файлу"
                               variant="outlined"
                               style={{width: "100%"}}
                               onChange={(event) => setFilename(event.target.value)}
                    />
                </Grid>
                <Grid item xs={2}>
                    <Button variant="contained"
                            size="large"
                            className={classes.btn}
                            onClick={() => onFileUpload(filename)}>
                        Выполнить анализ
                    </Button>
                </Grid>
                {console.log("result: ", result)}
                <Grid item xs={12}>
                    {result && !isResultEmpty(result) ?
                        <TableContainer component={Paper}>
                            <Table aria-label="simpler table">
                                <TableHead style={{ backgroundColor: "#d5d5d5" }}>
                                    <TableRow>
                                        <TableCell align="center">Суть характеристики</TableCell>
                                        <TableCell align="center">Значение нормы</TableCell>
                                        <TableCell align="center">Соответстие норме</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {getTableRow(result.MAX_CLASS_LENGTH)}
                                    {getTableRow(result.MAX_BODY_LENGTH)}
                                    {getTableRow(result.MAX_METHOD_NAME_LENGTH)}
                                    {getTableRow(result.MAX_PARAM_COUNT)}
                                    {getTableRow(result.MAX_VARIABLE_LENGTH)}
                                    {getTableRow(result.MAX_VARIABLE_COUNT)}
                                    {getTableRow(result.MAX_METHODS_COUNT)}
                                    {getTableRow(result.MIN_VARIABLE_LENGTH)}
                                    {getTableRow(result.BOOLEAN_STARTS_WITH_IS_HAS)}
                                    {getTableRow(result.NAMES_IN_CAMEL_CASE)}
                                    {getTableRow(result.MAX_SYMBOLS_COUNT_PER_LINE)}
                                    {getTableRow(result.MAX_NUMBER_OF_ACTIONS_PER_LINE)}
                                    {getTableRow(result.MAX_EMPTY_LINES_COUNT_PER_METHOD)}
                                </TableBody>
                            </Table>
                        </TableContainer>
                        : console.log("There are no data!")}
                </Grid>
            </Grid>
        </>
    );
}

ReactDOM.render(
    <Main/>,
    document.getElementById('react-mountpoint')
);
