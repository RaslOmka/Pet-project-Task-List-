package com.ogorod.notepadtask.data.storage.room.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ogorod.notepadtask.data.model.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {
    /**             ПРОВЕРИТЬ:
     * При выборе типа возвращаемого значения учитывайте допустимость значений NULL, так как это влияет на то,
    как метод запроса обрабатывает пустые таблицы:
    Когда тип возвращаемого значения равен Flow<T>, запрос к пустой таблице вызывает исключение нулевого указателя.
    Когда тип возвращаемого значения — Flow<T?>, запрос к пустой таблице выдает нулевое значение.
    Когда тип возвращаемого значения — Flow<List<T>>, запрос к пустой таблице выдает пустой список. */
    //сохранить атску
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    //вернуть все таски кроме почмеченых на удаление
    @Query("SELECT * FROM tasks WHERE tasks.`delete`=0")
    fun getAll(): Flow<List<Task>>

    //найти таски по совпадению слов
    @Query(
        "SELECT * FROM tasks WHERE tasks.`delete`=0 AND tasks.title LIKE :searchText OR " +
                "tasks.`delete`=0 AND tasks.text LIKE :searchText"
    )
    fun searchTextLike(searchText: String): Flow<List<Task>>

    //вернуть все таки отмеченные как избранные
    @Query("SELECT * FROM tasks WHERE tasks.favorite = 1 AND tasks.`delete`=0")
    fun loadAllTaskFavorite(): Flow<List<Task>>

    //вернуть все таски почмеченых на удаление
    @Query("SELECT * FROM tasks WHERE tasks.`delete`=1")
    fun loadAllTDeleteTask(): Flow<List<Task>>

    //удалить  таску(и)
    @Delete
    suspend fun delete(vararg tasks: Task)

    //обновить таску
    @Update
    suspend fun update(task: Task)

    //отметить таску(и) на удаление
    @Update
    suspend fun setTimerDelete(vararg tasks: Task)

    //отменить удаление таски
    @Update
    suspend fun cancelDeleteTask(task: Task)

    //отменить удаление всех тасок
    @Update
    suspend fun restoreAllDeleteTasks(vararg tasks: Task)


//    @Query("UPDATE tasks SET `delete`=1 AND timerDelete=0 WHERE tasks.`delete`=1")
//    suspend fun updateAllDeleteTask()

}