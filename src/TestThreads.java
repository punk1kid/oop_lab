public class TestThreads {
    public static void main() {
        Object mutex = new Object(); // Создаем монитор
        ThreadEx [] threads = new ThreadEx[5]; // Объявляем 5 потоков
/** Вызываем статический метод запуска потоков */
        startThreads(mutex, threads);
// Завершение потока main произойдет после завершения всех потоков
        joinThreads(threads);
        System.out.println("Основной поток завершен");
    }
    private static void startThreads (Object mutex, ThreadEx[] threads)
    { for (int i = 0; i < threads.length; i++)
    { threads[i] = new ThreadEx("Поток №" + i, mutex);
        threads[i].start();
    }}
    /** * Ожидание окончания потоков */
    private static void joinThreads(ThreadEx[] threads)
    { for (int i = 0; i < threads.length; i++)
        try { threads[i].join();
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}