import { createStore, applyMiddleware } from 'redux'
import rootReducer from './reducers'

const initialState = {
	fakeSins: []
}

function logger({ getState }) {
	return next => action => {
		console.log('will dispatch', action)

		const returnValue = next(action)

		console.log('state after dispatch', getState())

		return returnValue
	}
}

export const store = createStore(rootReducer, initialState, applyMiddleware(logger))
