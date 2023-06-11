import { type PayloadAction, createSlice } from '@reduxjs/toolkit'
import { type IHistorial } from '../../types/types'

const initialState = [] as IHistorial[]

export const historial = createSlice({
  name: 'historial',
  initialState,
  reducers: {
    setHistorial: (_, { payload }: PayloadAction<IHistorial[]>) => payload,
    addEntry: (state, { payload }: PayloadAction<IHistorial>) => {
      state.push(payload)
    }
  }
})

export const { addEntry, setHistorial } = historial.actions

export default historial.reducer
