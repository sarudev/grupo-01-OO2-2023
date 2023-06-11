import { configureStore } from '@reduxjs/toolkit'
import currentTab from './reducer/currentTab'
import modal from './reducer/modal'
import historial from './reducer/historial'
import dependencias from './reducer/dependencias'
import sensores from './reducer/sensores'

const store = configureStore({
  reducer: {
    currentTab,
    modal,
    historial,
    dependencias,
    sensores
  }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch

export default store
