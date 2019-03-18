export const ADD_FAKE_SIN_CARDS = 'ADD_FAKE_SIN_CARDS'
export const DELETE_FAKE_SIN_CARD = 'DELETE_FAKE_SIN_CARD'

export const addFakeSinCards = (cards) => {
	return ({
		type: ADD_FAKE_SIN_CARDS,
		cards: cards
	})
}

export const deleteFakeSinCard = (cardId) => ({
	type: DELETE_FAKE_SIN_CARD,
	idToDelete: cardId
})