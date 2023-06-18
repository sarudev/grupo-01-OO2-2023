import { type LoaderFunction, type Params } from 'react-router-dom'
import { type UserData, type LoaderResponse } from '../../types/types'
import axios, { AxiosError } from 'axios'
import { Routes } from '../../types/enums'

export default function loader (path: (params: Params<string>) => string): LoaderFunction {
  return async ({ params }) => {
    let response: LoaderResponse

    try {
      const res = await axios.get(Routes.BaseUrl + path(params), { withCredentials: true })
      const { data: userData } = await axios.get(Routes.BaseUrl + Routes.UserData, { withCredentials: true }) as { data: UserData }

      response = { lugar: res.data, status: res.status, userRole: userData.role, serverWorking: true }
    } catch (err: unknown) {
      console.log(err)
      if (err instanceof AxiosError) {
        const status = err.response?.status ?? 500
        response = { lugar: null, status, userRole: null, serverWorking: status !== 500 }
      }
      response = { lugar: null, status: 500, userRole: null, serverWorking: false }
    }

    return response
  }
}
