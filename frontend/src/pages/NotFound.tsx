import { Link } from 'react-router-dom'
import '../styles/notfound.scss'

export default function NotFound () {
  return (
    <div className="not-found">
      <div className="code">404</div>
      <div className="message">Page not found</div>
      <Link className='goto' to={'/'}>
        Return to homepage
      </Link>
    </div>
  )
}
