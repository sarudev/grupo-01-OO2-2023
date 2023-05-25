import { SensorType } from '../types/enums'
import { type Campus } from '../types/types'

export default {
  edificios: [
    {
      id: 1,
      nombre: 'José Hernández',
      aulas: [
        {
          id: 29,
          nombre: '11',
          sensores: [
            {
              id: 1,
              type: SensorType.Tiempo
            }
          ]
        }
      ],
      sensores: [
        {
          id: 1,
          type: SensorType.Tiempo
        }
      ]
    },
    {
      id: 2,
      nombre: 'Comedor Universitario Padre Mujica',
      aulas: [],
      sensores: []
    },
    {
      id: 3,
      nombre: 'Estudio de Grabación E.S. Discépolo',
      aulas: [],
      sensores: []
    },
    {
      id: 4,
      nombre: 'Lola Mora',
      aulas: [],
      sensores: []
    },
    {
      id: 5,
      nombre: 'Hernandez Arregui',
      aulas: [],
      sensores: []
    },
    {
      id: 6,
      nombre: 'Gimnasio Comunitario Gatica',
      aulas: [],
      sensores: []
    },
    {
      id: 7,
      nombre: 'Quincho Roberto Fontanarrosa',
      aulas: [],
      sensores: []
    },
    {
      id: 8,
      nombre: 'Juana Manso',
      aulas: [],
      sensores: []
    },
    {
      id: 9,
      nombre: 'Inadi',
      aulas: [],
      sensores: []
    },
    {
      id: 10,
      nombre: 'Campo de deportes Delfo Cabrera',
      aulas: [],
      sensores: []
    },
    {
      id: 11,
      nombre: 'Irma Laciar de Carrica',
      aulas: [],
      sensores: []
    },
    {
      id: 12,
      nombre: 'Leonardo Wethein',
      aulas: [],
      sensores: []
    },
    {
      id: 13,
      nombre: 'Oscar Varsavsky',
      aulas: [],
      sensores: []
    },
    {
      id: 14,
      nombre: 'Jardín Maternal A. Villaflor',
      aulas: [],
      sensores: []
    },
    {
      id: 15,
      nombre: 'Héctor Oesterheld',
      aulas: [],
      sensores: []
    },
    {
      id: 16,
      nombre: 'Cine Tita Merello',
      aulas: [],
      sensores: []
    },
    {
      id: 17,
      nombre: 'Casa del Estudiante',
      aulas: [],
      sensores: []
    },
    {
      id: 18,
      nombre: 'Lisandro de la Torre',
      aulas: [],
      sensores: []
    },
    {
      id: 19,
      nombre: 'Macedonio Fernández',
      aulas: [],
      sensores: []
    },
    {
      id: 20,
      nombre: 'Raúl Scalabrini Ortiz',
      aulas: [],
      sensores: []
    },
    {
      id: 21,
      nombre: 'Arturo Jauretche',
      aulas: [],
      sensores: []
    },
    {
      id: 22,
      nombre: 'Manuel Ugarte',
      aulas: [],
      sensores: []
    },
    {
      id: 23,
      nombre: 'Homero Manzi',
      aulas: [],
      sensores: []
    },
    {
      id: 24,
      nombre: 'Ortega Peña',
      aulas: [],
      sensores: []
    },
    {
      id: 25,
      nombre: 'Leopoldo Marechal',
      aulas: [],
      sensores: []
    },
    {
      id: 26,
      nombre: 'Juana Azurduy',
      aulas: [],
      sensores: []
    },
    {
      id: 27,
      nombre: 'Pascual Contursi',
      aulas: [],
      sensores: []
    },
    {
      id: 28,
      nombre: 'Néstor Kirchner',
      aulas: [],
      sensores: []
    }
  ],
  espaciosVerdes: [],
  parkings: []
} as Campus
