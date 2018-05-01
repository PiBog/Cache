package ua.pp.sola.caches.cache;

import ua.pp.sola.caches.entity.Entity;

public class CacheEntity  {

        private int frequence;
        private final Entity entity;
//        private final String key;

        public CacheEntity(final Entity entity) {
            this.entity = entity;
            this.frequence = 0;
//            this.key = entity.getId();
        }

        /**
         * Returns the saved instance of {@link Entity}.
         *<p>
         * @return  {@link Entity}
         */
        public Entity getEntity () {
            return this.entity;
        }


        /**
         * Returns value of use frequency this instance {@link Entity}.
         *<p>
         * @return  frequence;
         */
        public int getFrequence() {
            return this.frequence;
        }

//        /**
//         * Returns the unique ID of instance.
//         *<p>
//         * @return  ID key.
//         */
//        public String getKey() {
//            return this.key;
//        }

        protected void addFrequence(){
            ++this.frequence;
        }

}
