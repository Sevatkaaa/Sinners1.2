import React, {useState} from 'react'
import { connect } from 'react-redux'
import { addFakeSinCards } from '../store/actions'
import SinCard from '../components/SinCard'
import UserLogin from './UserLogin'
import { IconButton, Grid, Dialog } from '@material-ui/core';
import { Schedule, Accessible, VerifiedUserRounded } from '@material-ui/icons';
import { store } from '../store/store'
import './Sin.css'
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import Avatar from '@material-ui/core/Avatar';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import ListItemText from '@material-ui/core/ListItemText';
import DialogTitle from '@material-ui/core/DialogTitle';
import {PersonIcon, AccountCircle} from '@material-ui/icons/';
import AddIcon from '@material-ui/icons/Add';
import Typography from '@material-ui/core/Typography';
import blue from '@material-ui/core/colors/blue';

const SignInDialogIcon = () => {
    console.log("Hello world")
    const [isDialogOpened, setDialogOpened] = useState(false)
    return (
        <Grid item container alignItems="center" justify="center" direction="column">
            <IconButton onClick={()=> setDialogOpened(true)}>
                <AccountCircle />
            </IconButton>
            <Dialog
                open={isDialogOpened}
                onClose={() => setDialogOpened(false)}
            >
                <DialogTitle style={{height: "12px"}}>Sign in</DialogTitle>
                <UserLogin/>
            </Dialog>
        </Grid>
    )
}

const isAuthenticated = () => {
    return false
}

const Header = (props) => {
    // We should check weather user is authentificated
    // And provide correct icon in top right corner
    const isUserLogin = isAuthenticated()

    return (
        <Grid container spacing={24}
            className="header"
            direction="row"
            justify="flex-start"
            alignItems="flex-start"
        >
            <Grid item xs={8} container direction="column" justify="space-between">
                <Grid item>
                    <p style={{ fontSize: "48px", color: "#ffffff", marginTop: '24px'}}>
                        <b>{"SIN"}</b> NERS
                    </p>
                </Grid>
            </Grid>
            <Grid item xs />
            <Grid item>
                <div>
                    <SignInDialogIcon />
                </div>
            </Grid>
        </Grid>
    )
}

Header.propTypes = {
}


export default Header
