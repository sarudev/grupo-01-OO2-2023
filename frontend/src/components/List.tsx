export default function List <T> ({ array, itemName, children, dependencia }: { array: T[], itemName: string, children: (lugar: T) => JSX.Element, dependencia?: boolean }) {
  if (array == null || array.length < 1) {
    return (
      <div className='list empty'>No hay {itemName} para mostrar.</div>
    )
  }

  return (
    <div className='list'>
      {array.map((e, i) => {
        return (
          <div key={i} className={`item ${dependencia != null && dependencia ? 'dependencia' : itemName}`}>
            {children(e)}
          </div>
        )
      })}
    </div>
  )
}
