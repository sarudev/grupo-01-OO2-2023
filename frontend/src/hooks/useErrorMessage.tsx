import { useState, useEffect } from 'react'

export default function useErrorMessage () {
  const [error, setError] = useState('')

  useEffect(() => {
    const id = setTimeout(() => {
      setError('')
    }, 5000)

    return () => {
      clearTimeout(id)
    }
  }, [error])

  const Message = () => <>{error.length > 0 && <div className='error'>{ error }</div>}</>

  return {
    Message,
    error,
    setError
  }
}
