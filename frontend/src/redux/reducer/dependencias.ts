import { type PayloadAction, createSlice, type Draft } from '@reduxjs/toolkit'
import { type IAula, type IEstacionamiento } from '../../types/types'

type State = Draft<Array<IAula | IEstacionamiento> | null>

const initialState: State = [] as State

export const dependencias = createSlice({
  name: 'dependencias',
  initialState,
  reducers: {
    setDependencias: (_, { payload }: PayloadAction<IAula[] | IEstacionamiento[] | null>) => payload,
    addEntry: (state, { payload }: PayloadAction<IAula | IEstacionamiento>) => {
      if (state == null) return
      state.push(payload)
    }
  }
})

export const { addEntry, setDependencias } = dependencias.actions

export default dependencias.reducer
