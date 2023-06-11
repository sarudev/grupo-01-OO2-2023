import { createSlice } from '@reduxjs/toolkit'

const initialState = false

export const modal = createSlice({
  name: 'modal',
  initialState,
  reducers: {
    openModal: () => true,
    closeModal: () => false
  }
})

export const { closeModal, openModal } = modal.actions

export default modal.reducer
