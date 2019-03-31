import React from 'react'
import { connect } from 'react-redux'
import { addFakeSinCards } from '../store/actions'
import SinCard from '../components/SinCard'
import { IconButton, Grid } from '@material-ui/core';
import { Schedule, Accessible } from '@material-ui/icons';
import { store } from '../store/store'

const ErrorView = () => (
    <Grid container
        className='error'
        direction="column"
        justify="flex-start"
        alignItems="flex-start"
    >
        <Grid item>
            <p style={{color: '#d32f2f', marginBottom: '24px', fontSize: '40px'}}>
                404 – Page Not Found
            </p>
        </Grid>
        <Grid item>
            <p style={{color: '#5f6368', marginBottom: '72px', fontSize: '24px'}}>
                Don’t worry, we’ll help you get where you need to go. Try searching again, or using the links below to find what you’re looking for:
            </p>
            <p style={{color: '#5f6368', marginBottom: '8px', fontSize: '16px'}}>
                POPULAR LINKS
            </p>
            <ul style={{fontSize: '20px', margin: '0px', padding: '0px'}}>
                <li
                    style={{marginBottom: '8px', display: 'list-item', listStyleType: 'none'}}
                >
                    <a style={{textDecoration: 'none', color: '#202124'}} href='/dashboard'>
                        Dashboard
                    </a>
                </li>
                <li
                    style={{marginBottom: '8px', display: 'list-item', listStyleType: "none"}}
                >
                    <a style={{textDecoration: 'none', color: '#202124'}} href='/bible'>
                        Bible
                    </a>
                </li>
            </ul>
        </Grid>
    </Grid>
)

export default ErrorView;