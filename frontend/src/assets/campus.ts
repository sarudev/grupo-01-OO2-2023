import { SensorType } from '../types/enums'
import { type Campus as ICampus } from '../types/types'

export default {
  edificios: [
    {
      tipo: 'edificio',
      id: 1,
      nombre: 'José Hernández',
      luces: true,
      aulas: [
        {
          tipo: 'aula',
          id: 29,
          nombre: '11',
          luces: true,
          sensores: [
            {
              id: 1,
              tipo: SensorType.Tiempo,
              activo: true
            }
          ],
          historial: [
            {
              tipo: SensorType.Tiempo,
              mensaje: 'Luces encendidas',
              fecha: Date.now()
            }
          ]
        }
      ],
      sensores: [
        {
          id: 1,
          tipo: SensorType.Tiempo,
          activo: false
        }
      ],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 2,
      nombre: 'Comedor Universitario Padre Mujica',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 3,
      nombre: 'Estudio de Grabación E.S. Discépolo',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 4,
      nombre: 'Lola Mora',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 5,
      nombre: 'Hernandez Arregui',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 6,
      nombre: 'Gimnasio Comunitario Gatica',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 7,
      nombre: 'Quincho Roberto Fontanarrosa',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 8,
      nombre: 'Juana Manso',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 9,
      nombre: 'Inadi',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 10,
      nombre: 'Campo de deportes Delfo Cabrera',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 11,
      nombre: 'Irma Laciar de Carrica',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 12,
      nombre: 'Leonardo Wethein',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 13,
      nombre: 'Oscar Varsavsky',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 14,
      nombre: 'Jardín Maternal A. Villaflor',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 15,
      nombre: 'Héctor Oesterheld',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 16,
      nombre: 'Cine Tita Merello',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 17,
      nombre: 'Casa del Estudiante',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 18,
      nombre: 'Lisandro de la Torre',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 19,
      nombre: 'Macedonio Fernández',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 20,
      nombre: 'Raúl Scalabrini Ortiz',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 21,
      nombre: 'Arturo Jauretche',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 22,
      nombre: 'Manuel Ugarte',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 23,
      nombre: 'Homero Manzi',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 24,
      nombre: 'Ortega Peña',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 25,
      nombre: 'Leopoldo Marechal',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 26,
      nombre: 'Juana Azurduy',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 27,
      nombre: 'Pascual Contursi',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    },
    {
      tipo: 'edificio',
      id: 28,
      nombre: 'Néstor Kirchner',
      luces: true,
      aulas: [],
      sensores: [],
      historial: []
    }
  ],
  espaciosVerdes: [],
  parkings: []
} as ICampus
