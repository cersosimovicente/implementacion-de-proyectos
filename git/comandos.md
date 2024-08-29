| **Comando**                      | **Descripción**                                                                 |
|----------------------------------|---------------------------------------------------------------------------------|
| `git config --global user.name "Tu Nombre"` | Configura el nombre de usuario globalmente en Git.                             |
| `git config --global user.email "tuemail@ejemplo.com"` | Configura el correo electrónico globalmente en Git.                         |
| `git clone https://github.com/usuario/repositorio.git` | Clona un repositorio existente a tu máquina local.                            |
| `git checkout -b nueva-rama`     | Crea y cambia a una nueva rama.                                                 |
| `git pull origin main`           | Actualiza tu repositorio local con los últimos cambios del repositorio remoto.  |
| `git status`                     | Verifica el estado de tu repositorio local y los cambios realizados.            |
| `git add archivo.txt`            | Añade un archivo específico a la zona de preparación (staging).                 |
| `git add .`                      | Añade todos los cambios realizados a la zona de preparación.                    |
| `git commit -m "Descripción de los cambios realizados"` | Confirma los cambios en la rama actual con un mensaje descriptivo.  |
| `git push origin nueva-rama`     | Sube tus cambios a la rama correspondiente en el repositorio remoto.            |
| `git checkout main`              | Cambia a la rama principal.                                                     |
| `git merge nueva-rama`           | Fusiona la rama especificada con la rama actual.                                |
| `git checkout -- archivo.txt`    | Deshace los cambios realizados en un archivo específico.                        |
| `git revert id_del_commit`       | Revierta un commit específico manteniendo el historial de cambios.              |
| `git log --oneline`              | Muestra un historial de commits en una sola línea por cada commit.              |
| `git remote add nombre_remoto https://github.com/usuario/otro-repo.git` | Añade un nuevo repositorio remoto. |
| `git fetch nombre_remoto`        | Obtiene los cambios más recientes de un repositorio remoto sin fusionarlos.     |
| `git branch -d rama-que-no-necesitas` | Elimina una rama local que ya no necesitas.                                  |
| `git remote prune origin`        | Elimina las referencias a ramas remotas eliminadas del repositorio remoto.      |

