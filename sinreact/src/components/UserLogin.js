import React from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import withStyles from '@material-ui/core/styles/withStyles';
import { IconButton, Grid } from '@material-ui/core';
// import { Link } from 'react-router-dom'


const styles = theme => ({
    main: {
        width: 'auto',
        display: 'block', // Fix IE 11 issue.
        marginLeft: theme.spacing.unit * 3,
        marginRight: theme.spacing.unit * 3,
        [theme.breakpoints.up(400 + theme.spacing.unit * 3 * 2)]: {
            width: 400,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    },
    paper: {
        marginTop: '24px',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        padding: `${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px`,
    },
    avatar: {
        margin: theme.spacing.unit,
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing.unit,
    },
    submit: {
        marginTop: theme.spacing.unit * 3,
    },
});

const validateUserAndChangeView = () => {
    if (true) {
        
    }
}

const UserLogin = (props) => {
    const { classes } = props;
    console.log("Buildin' View!")

        return (
            <main className={classes.main}>
            <CssBaseline />
            <Paper className={classes.paper}>
                <form className={classes.form}>
                    <FormControl margin="normal" required fullWidth>
                        <InputLabel htmlFor="username">Sinner</InputLabel>
                        <Input id="username" name="username" autoComplete="username" autoFocus />
                    </FormControl>
                    <FormControl margin="normal" required fullWidth>
                        <InputLabel htmlFor="password">Do not tell anyone</InputLabel>
                        <Input name="password" type="password" id="password" autoComplete="current-password" />
                    </FormControl>
                    <FormControlLabel
                        control={<Checkbox value="remember" color="secondary" />}
                        label="Remember me"
                    />
                    <Button
                        //type="submit"
                        //component={Link}
                        fullWidth
                        variant="contained"
                        color="secondary"
                        className={classes.submit}
                        onClick={() => {console.log("Privet poshel nahoy")}}
                    >
                        Sign in
                    </Button>
                </form>
            </Paper>
        </main>
    )
}

UserLogin.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(UserLogin);
