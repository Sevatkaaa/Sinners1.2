import {ADD_FAKE_SIN_CARDS, DELETE_FAKE_SIN_CARD} from './actions'
import {combineReducers} from 'redux'

const fakeSinsReducer = (fakeSins, action) => {
	fakeSins = fakeSins || []
	switch (action.type) {
		case ADD_FAKE_SIN_CARDS:
			return [...fakeSins, ...action.cards];
		case DELETE_FAKE_SIN_CARD:
			return fakeSins.filter((sin) => sin.id != action.idToDelete);
	}
	return fakeSins
}

export default combineReducers({
	fakeSins : fakeSinsReducer
})