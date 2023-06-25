import { type PayloadAction, createSlice } from '@reduxjs/toolkit'
import { type ISensor } from '../../types/types'

const initialState = [] as ISensor[]

export const sensores = createSlice({
  name: 'sensores',
  initialState,
  reducers: {
    setSensores: (_, { payload }: PayloadAction<ISensor[]>) => payload,
    addEntry: (state, { payload }: PayloadAction<ISensor>) => {
      state.push(payload)
    }
  }
})

export const { addEntry, setSensores } = sensores.actions

export default sensores.reducer
