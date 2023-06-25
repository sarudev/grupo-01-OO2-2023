import { redirect, type LoaderFunction } from 'react-router-dom'
import axios from 'axios'
import { Routes } from '../../types/enums'

export default function IndexLoader (): LoaderFunction {
  return async () => {
    const { data: loadedDB } = await axios.get(Routes.BaseUrl + '/loadedDB') as { data: boolean }
    console.log(loadedDB)

    if (!loadedDB) return redirect(Routes.LoadDB)

    return null
  }
}
