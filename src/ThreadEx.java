public class ThreadEx extends Thread {
        private Object mutex;

        public ThreadEx(String name, Object mutex) { // Задание имени потока и его приоритета
            this.mutex = mutex;
            setName(name);
            setPriority(MIN_PRIORITY);
        }

        /**
         * Основной метод потока
         */
        public void run() {
            for (int i = 0; i < 10; i++) { // Доступ к разделяемому коду через монитор
                synchronized (mutex) {
                    System.out.println(getName() + " работает");
                    try {
// Для эмуляции задержки доступа к ресурсу и большей наглядности
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
// Сообщаем монитору, что он освобождается
                    mutex.notify();
                }
                try {
// Имитируем работу после освобождения ресурса
// Без этой строчки следующий поток может не успеть захватить ресурс
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
