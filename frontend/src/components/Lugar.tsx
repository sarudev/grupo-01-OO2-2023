import Icon from './Icon'

interface ILugar {
  svgName: string
  title: string
  description: string
  tabs: () => JSX.Element
  content: () => JSX.Element
}

export default function Lugar ({ svgName, title, description, tabs, content }: ILugar) {
  return (
    <div className='container'>
      <div className="top">
        <div className="icon"><Icon svgName={svgName} /></div>
        <div className="text">
          <div className="title">{title}</div>
          <div className="description">{description}</div>
        </div>
      </div>
      <div className="bot">
        <nav>
          {tabs()}
        </nav>
        <div className="content">
          {content()}
        </div>
      </div>
    </div>
  )
}
