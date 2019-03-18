import React, { useState } from 'react';
import Grid from '@material-ui/core/Grid';
import './Sin.css';
import { LinearProgress, Typography, IconButton, Divider, CardHeader, Avatar, Card, CardContent } from '@material-ui/core';
import { ThreeSixty, } from '@material-ui/icons';
import { createMuiTheme, MuiThemeProvider } from '@material-ui/core/styles';

const theme = createMuiTheme({
	palette: {
		primary: {
			main: '#616161',
		},
		secondary: {
			light: '#F5F5F5',
			main: '#757575',
			contrastText: '#212121',
		},
		contrastThreshold: 3,
		tonalOffset: 0.2,
	},
});

const SinCard = (props) => {
	const [count, setCount] = useState(0);
	const sin = props.sin || {id:100, username: "Mark", sinType: "Drocha", points: 10, sinText: "Podrochiv" }
	const onClick = props.onClick || (() => {})
	const isRaised = count < 100;

	return (
		<Card className="sin-card" raised={isRaised}>
			<CardHeader
				avatar={<Avatar style={{ backgroundColor: "#FF5722" }}> {sin.username[0]} </Avatar>}
				title={sin.sinType}
				subheader={sin.username}
				action={sin.points + "pts"}
			>
			</CardHeader>
			<Divider />
			<CardContent style={{ padding: "8px" }}>
				<Grid container>
					<Grid item xs zeroMinWidth>
						<Typography noWrap>{sin.sinText}</Typography>
					</Grid>
					<Grid children>
						<IconButton disabled={!isRaised} onClick={() => {
							setCount(count + sin.points)
							onClick()
						}}>
							<ThreeSixty />
						</IconButton>
					</Grid>
				</Grid>
			</CardContent>
			<MuiThemeProvider theme={theme}>
				<LinearProgress value={count} variant="determinate" />
			</MuiThemeProvider>
		</Card>
	)
}


export default SinCard;