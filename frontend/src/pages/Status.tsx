import { useLayoutEffect } from 'react'
import { Link } from 'react-router-dom'
import '../styles/notfound.scss'

export default function Status ({ code, statusMessage, goto, gotoMessage, gotoState }: { code: number, statusMessage: string, goto: string, gotoMessage: string, gotoState?: string }) {
  useLayoutEffect(() => {
    document.title = statusMessage + ' :('
  }, [])

  return (
    <div className="not-found">
      <div className="code">{code}</div>
      <div className="message">{statusMessage}</div>
      <Link className='goto' to={goto} replace state={{ from: gotoState }}>
        {gotoMessage}
      </Link>
    </div>
  )
}
