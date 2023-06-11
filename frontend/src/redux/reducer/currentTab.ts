import { createSlice, type PayloadAction } from '@reduxjs/toolkit'

const initialState = ''

export const currentTab = createSlice({
  name: 'currentTab',
  initialState,
  reducers: {
    setTab: (_, { payload }: PayloadAction<string>) => payload
  }
})

export const { setTab } = currentTab.actions

export default currentTab.reducer
