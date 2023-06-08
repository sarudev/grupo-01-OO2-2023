import { SensorType } from '../types/enums'
import { type Campus } from '../types/types'

export default {
  edificios: [
    {
      tipo: 'edificio',
      id: 1,
      nombre: 'José Hernández',
      aulas: [
        {
          tipo: 'aula',
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
      tipo: 'edificio',
      id: 2,
      nombre: 'Comedor Universitario Padre Mujica',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 3,
      nombre: 'Estudio de Grabación E.S. Discépolo',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 4,
      nombre: 'Lola Mora',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 5,
      nombre: 'Hernandez Arregui',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 6,
      nombre: 'Gimnasio Comunitario Gatica',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 7,
      nombre: 'Quincho Roberto Fontanarrosa',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 8,
      nombre: 'Juana Manso',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 9,
      nombre: 'Inadi',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 10,
      nombre: 'Campo de deportes Delfo Cabrera',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 11,
      nombre: 'Irma Laciar de Carrica',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 12,
      nombre: 'Leonardo Wethein',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 13,
      nombre: 'Oscar Varsavsky',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 14,
      nombre: 'Jardín Maternal A. Villaflor',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 15,
      nombre: 'Héctor Oesterheld',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 16,
      nombre: 'Cine Tita Merello',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 17,
      nombre: 'Casa del Estudiante',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 18,
      nombre: 'Lisandro de la Torre',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 19,
      nombre: 'Macedonio Fernández',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 20,
      nombre: 'Raúl Scalabrini Ortiz',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 21,
      nombre: 'Arturo Jauretche',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 22,
      nombre: 'Manuel Ugarte',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 23,
      nombre: 'Homero Manzi',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 24,
      nombre: 'Ortega Peña',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 25,
      nombre: 'Leopoldo Marechal',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 26,
      nombre: 'Juana Azurduy',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 27,
      nombre: 'Pascual Contursi',
      aulas: [],
      sensores: []
    },
    {
      tipo: 'edificio',
      id: 28,
      nombre: 'Néstor Kirchner',
      aulas: [],
      sensores: []
    }
  ],
  espaciosVerdes: [],
  parkings: []
} as Campus
