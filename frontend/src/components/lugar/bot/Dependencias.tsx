import { useState, useLayoutEffect } from 'react'
import List from '../../List'
import type { IAula, IEstacionamiento, Lugares } from '../../../types/types'
import { Link } from 'react-router-dom'
import { useAppSelector } from '../../../hooks/Redux.'
import Modal from '../../Modal'
import AddLugar from '../../modal/AddLugar'
import AddButton from '../../AddButton'
import useLugarDependencia from '../../../hooks/useLugarDependencia'
import useInput from '../../../hooks/useInput'
import { UserRole } from '../../../types/enums'

export default function Dependencias ({ lugar, userRole, visible }: { lugar: Lugares, userRole: UserRole, visible: boolean }) {
  const dependencias = useAppSelector(s => s.dependencias)
  const [sortedDependencias, setSortedDependencias] = useState<IAula[] | IEstacionamiento[]>(dependencias as IAula[] | IEstacionamiento[])
  const { withoutS: nombreDependencia, withS: nombreDependenciaS } = useLugarDependencia(lugar.tipo)
  const { Input, input } = useInput(`Nombre del ${nombreDependenciaS!}`)

  useLayoutEffect(() => {
    if (dependencias == null) return
    const deps = dependencias.toSorted((a, b) => a.nombre.localeCompare(b.nombre)).filter(e => e.nombre.includes(input)) as IAula[] | IEstacionamiento[]
    setSortedDependencias(deps)
  }, [input, dependencias])

  if (!visible) return null

  return (
    <>
      <div className='filter dependencias'>
        <div className='text'>Filtrar: </div>
        <Input />
      </div>
      <List<IAula | IEstacionamiento> array={sortedDependencias} itemName={nombreDependencia!} dependencia>
        {(d) => {
          if (d == null) return <></>
          return (
            <>
              <div className="nombre">{d.nombre}</div>
              <Link className='link' to={`${nombreDependenciaS!}/${d.nombre}`}>
                Visitar
              </Link>
            </>
          )
        }}
      </List>
      {userRole === UserRole.Admin && <AddButton text={nombreDependenciaS!} />}
      <Modal>
        <AddLugar lugar={lugar} />
      </Modal>
    </>
  )
}
