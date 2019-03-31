import React from 'react'
import { connect } from 'react-redux'
import { addFakeSinCards } from '../store/actions'
import SinCard from '../components/SinCard'
import { IconButton, Grid, Typography } from '@material-ui/core';
import { Cloud, Accessible } from '@material-ui/icons';
import { store } from '../store/store'

var counter = 0
const makeSin = () => {
    const types = [
        'Смертный грех',
        'Мастурбация',
        'Мастурбация на лекции',
        'Задушить удава',
        'Сказать, что бога нет',
        'Анальный секс в подъезде'
    ]
    const sinTexts = [
        'Должно быть весело',
        'Одну жизнь живем',
        'Го разок за Домбасс',
        'Ну наверное все так делают'
    ]
    const usernames = [
        'markiro420',
        'beeffallo',
        'sevattka1337',
        'TOXA_KASTET',
        'evrei1488'
    ]
    return ({
        id: counter++,
        username: usernames[Math.floor(Math.random() * usernames.length)],
        sinType: types[Math.floor(Math.random() * types.length)],
        points: Math.floor(Math.random() * 23) + 1,
        sinText: sinTexts[Math.floor(Math.random() * sinTexts.length)],
    })
}

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
                                    store.dispatch(addFakeSinCards([makeSin("Mark", "Drocha", "Hello from redux")]))
                                }}
                            >
                                <Cloud color='primary'/>
                            </IconButton>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid container justify="center">
                    {sins.map((sin) => (<SinCard key={sin.id} dashId={sin.id} sin={sin} />))}
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