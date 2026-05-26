# Calculadora Java

Proyecto de ejemplo para la materia **Ingeniería de Software**.
Implementa una calculadora simple con tests unitarios y pipelines de CI/CD usando GitHub Actions.

## Operaciones

| Método | Descripción |
|--------|-------------|
| `suma(a, b)` | Retorna `a + b` |
| `resta(a, b)` | Retorna `a - b` |
| `multiplica(a, b)` | Retorna `a * b` |
| `divide(a, b)` | Retorna `a / b`. Lanza `IllegalArgumentException` si `b == 0` |

## Estructura del proyecto

```
.
├── .github/
│   ├── actions/
│   │   ├── build/action.yml    ← action reutilizable: compilar
│   │   └── test/action.yml     ← action reutilizable: correr tests
│   └── workflows/
│       ├── main.yml            ← CI: se dispara en PR hacia main
│       └── release.yml         ← CD: se dispara al crear un tag
├── src/
│   ├── main/java/com/calc/Calculator.java
│   └── test/java/com/calc/CalculatorTest.java
├── pom.xml
└── README.md
```

## Cómo correr los tests localmente

```bash
mvn test
```

## Pipelines de GitHub Actions

### CI — `main.yml`

Se dispara automáticamente en cada **Pull Request hacia `main`**.

Corre dos jobs en secuencia:
1. **build** — compila el proyecto con `mvn compile`
2. **test** — corre los tests con `mvn test` (requiere que build pase primero)

Ambos jobs son visibles como checks en el PR. Si alguno falla, el merge queda bloqueado.

### CD — `release.yml`

Se dispara al crear un **tag de versión** (`v1.0.0`, `v1.2.3`, etc.).

```bash
git tag v1.0.0
git push origin v1.0.0
```

Empaqueta el JAR y publica un **GitHub Release** con el artefacto adjunto.

## Flujo de trabajo recomendado

```
feature/xxx  →  develop  →  (PR)  →  main  →  tag  →  Release
```

1. Trabajar en una rama `feature/` o directamente en `develop`.
2. Abrir un PR de `develop` → `main`.
3. El CI corre automáticamente y valida el código.
4. Si los checks pasan, mergear el PR.
5. Para publicar una versión: crear un tag y pushearlo.
