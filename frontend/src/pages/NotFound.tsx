import { Link, useNavigate } from 'react-router-dom'

export default function NotFound () {
  const navigate = useNavigate()

  return (
    <div className="not-found">
      <div className="code">404</div>
      <div className="message">Page not found</div>
      <div className='links'>
        <Link className='goto' to={'/'}>
          Return to homepage
        </Link>
        <Link className='back' to={'/'} onClick={(e) => {
          e.preventDefault()
          navigate(-1)
        }}>
          Go back
        </Link>
      </div>
    </div>
  )
}
