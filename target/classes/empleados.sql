-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-12-2023 a las 00:55:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empleados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `ID` int(11) NOT NULL,
  `CARGO` varchar(255) DEFAULT NULL,
  `FECHAINICIO` date DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PRIMERAPELLIDO` varchar(255) DEFAULT NULL,
  `SALARIO` double DEFAULT NULL,
  `SEGUNDOAPELLIDO` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`ID`, `CARGO`, `FECHAINICIO`, `NOMBRE`, `PRIMERAPELLIDO`, `SALARIO`, `SEGUNDOAPELLIDO`) VALUES
(1, 'Tecnico', '2023-12-28', 'David', 'Fernandez', 32000, 'Comesana'),
(2, 'Encargado', '2023-12-28', 'Sofia', 'Garrido', 34000, 'Garrido'),
(3, 'Ayudante', '2023-12-28', 'Paco', 'Garcia', 28000, 'Gonzalez'),
(4, 'Ayudante', '2023-12-28', 'Manuel', 'Ferreiro', 27000, 'Rodriguez');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
