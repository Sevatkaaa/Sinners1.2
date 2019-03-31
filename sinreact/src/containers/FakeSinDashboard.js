import React from 'react'
import { connect } from 'react-redux'
import { addFakeSinCards } from '../store/actions'
import SinCard from '../components/SinCard'
import { IconButton, Grid, Typography } from '@material-ui/core';
import { Cloud, Accessible } from '@material-ui/icons';
import { store } from '../store/store'

var counter = 0
const makeSin = (username, type, points, text) => ({ id: counter++, username: username, sinType: type, points: points, sinText: text })

const FakeSinDashboard = (props) => {
    const sins = props.fakeSins || []
return (
        <div>
            <Grid container>
                <Grid container className="sins-dashboard-header"
                    direction="row"
                    justify="flex-start"
                    alignItems="flex-start"
                >
                    <Grid item>
                        <Typography style={{ fontSize: "32px", color: "#373737", verticalAlign: "center" }}><b>{"My "}</b> {sins.length} <b>{"SINS"}</b></Typography>
                    </Grid>
                    <Grid item xs />
                    <Grid item>
                        <Grid container alignItems="center" justify="center" direction="column">
                            <IconButton
                                onClick={() => {
                                    store.dispatch(addFakeSinCards([makeSin("Mark", "Drocha", 22, "Hello from redux")]))
                                }}
                            >
                                <Cloud color='primary'/>
                            </IconButton>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container justify="center">
                    {sins.map((sin, i) => (<SinCard key={i} dashId={i} sin={sin} />))}
                </Grid>
            </Grid>
        </div>
    )
}

const mapStateToProps = (state) => ({
    fakeSins: state.fakeSins
})

export default connect(
    mapStateToProps
)(FakeSinDashboard)